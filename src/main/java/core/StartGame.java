package core;

import java.util.HashMap;
import java.util.Map;

import static utilities.Constants.*;
import static utilities.Constants.GridOperationtype.ADD_OBSTACLE;
import static utilities.Constants.GridOperationtype.ADD_PLAYER;
import static utilities.Constants.TrapType.*;
import exceptions.BoundaryCaseException;
import exceptions.InvalidMoveException;
import fx.GameViewController;
import utilities.BoardPrinterCMD;
import utilities.InitialPlayerPositionService;
import utilities.InitialTrapPlacementService;

/**
 * @author Nithin
 * Start Game is the main class for the actual game logic
 */
public class StartGame {
    /**
     * Board object used in the entire game
     */
    public static Board b;
    /**
     * List to hold players and their number of times to be in pit
     */
    public static Map<Player, Integer> playersInTarPit = new HashMap<>();
    /**
     * Java FX view controller from main game screen
     */
    public static GameViewController gvc;

    /**
     * @param gameViewController
     * Main method is called by the Java FX to initialize the backend
     */
    public static void main(GameViewController gameViewController) {
        gvc = gameViewController;
        b = new Board(rows, columns);
        InitialPlayerPositionService.placePlayers();
        for (Player p : allPlayers) {
            b.addPlayerToPosition(p.getPositionX(), p.getPositionY(), p);
        }
        InitialTrapPlacementService.placeObstacles(b);
        BoardPrinterCMD.printBoard();
    }


    /**
     * @param player
     * @return Boolean
     * Shall check the eligibility of player
     * False if he is in pit and the count is not 0
     * True if he is not in pit
     */
    public static boolean isEligibleToRoll(Player p) {
        Integer noOfTimes = playersInTarPit.get(p);
        if (null == noOfTimes) {
            return true;
        }
        if (noOfTimes > 0) {
            noOfTimes--;
            playersInTarPit.put(p, noOfTimes);
            return false;
        } else if (noOfTimes == 0) {
            playersInTarPit.remove(p);
            return true;
        } else {
            return true;
        }
    }

    /**
     * @param player
     * @param moveRollVal
     * @param dirRollVal
     * @return
     * @throws BoundaryCaseException
     * Validates the roll, moves the player and calculates the score for it
     */
    public static boolean validateMoveRoll(Player p, int moveRollVal, Directions dirRollVal) throws BoundaryCaseException {
        int x = p.getPositionX();
        int y = p.getPositionY();
        int pointsGained = moveRollVal * 10;
        int[] tempXY = new int[2];
        int tempX, tempY;
        b.removePlayerFromPosition(x, y);
        if (b.getPosition(x, y).getTrapType() == TAR_PIT) {
            gvc.modifyGridTile(ADD_OBSTACLE, p.getPositionX(), p.getPositionY(), null, TAR_PIT);
        }
        try {
            tempXY = move(x, y, moveRollVal, dirRollVal);
        } catch (InvalidMoveException e) {
            System.out.println("Cannot move " + p.getName());
        }
        tempX = tempXY[0];
        tempY = tempXY[1];
        if (isBoundaryCaseAfterMove(tempX, tempY)) {
            System.out.println("Its Boundary case... Sorry you missed the chance");
            tempX = x;
            tempY = y;
        }
        if (x != tempX || y != tempY) {
            p.updateScore(pointsGained);
        }


        p.setPositionX(tempX);
        p.setPositionY(tempY);
        b.addPlayerToPosition(tempX, tempY, p);
        gvc.modifyGridTile(ADD_PLAYER, tempX, tempY, p, NONE);
        if (b.getTrapType(p.getPositionX(), p.getPositionY()) != NONE) {
            int currentPlayerIndex = playersInitialPos.indexOf(p);
            if (currentPlayerIndex > -1) {
                Player initialPlayer = playersInitialPos.get(currentPlayerIndex);
                b.getTrap(p.getPositionX(), p.getPositionY()).applyTrapRules(b, gvc, x, y, p, initialPlayer);
            }
        }
        return true;
    }

    /**
     * @param x
     * @param y
     * @param moveVal
     * @param isRight
     * @return
     * Moves the player horizontally in case of fence and player as obstacle
     * asks the user input to change the direction to Up, Stay or Down
     */
    public static int[] moveHorizontally(int x, int y, int moveVal, Boolean isRight) {
        int i = x;
        int j = y;
        while (moveVal > 0) {
            if (isRight) {
                j++;
                moveVal--;
                if (isBoundaryCaseAfterMove(i, j) || validateFenceAndPlayer(i, j)) {
                    b.addTempPlayerToPosition(i, j - 1);
                    gvc.enableButtonsForMovement("U/D/S");
                    String userString = gvc.playerMoveResponse();
                    b.removePlayerFromPosition(i, j - 1);
                    if (userString.equalsIgnoreCase("S")) {
                        j--;
                        break;
                    }
                    Boolean isForward =
                            userString.equalsIgnoreCase(
                                    "U");
                    j--;
                    moveVal++;
                    return moveVertically(i, j, moveVal, isForward);
                }
            } else {
                j--;
                moveVal--;
                if (isBoundaryCaseAfterMove(i, j) || validateFenceAndPlayer(i, j)) {
                    b.addTempPlayerToPosition(i, j + 1);
                    gvc.enableButtonsForMovement("U/D/S");
                    String userString = gvc.playerMoveResponse();
                    b.removePlayerFromPosition(i, j + 1);
                    if (userString.equalsIgnoreCase("S")) {
                        j++;
                        break;
                    }
                    Boolean isForward = userString.equalsIgnoreCase("U");

                    j++;
                    moveVal++;
                    return moveVertically(i, j, moveVal, isForward);
                }
            }
        }
        return new int[]{i, j};
    }

    /**
     * @param i
     * @param j
     * @return
     * Validates the position with Fence or player
     * if so returns true else returns false
     */
    public static Boolean validateFenceAndPlayer(int i, int j) {
        return (b.getTrapType(i, j) == FENCE) || (b.getPosition(i, j).getPlayer() != null);
    }

    /**
     * @param x
     * @param y
     * @param moveVal
     * @param isForward
     * @return
     * Moves the player vertically in case of fence and player as obstacle
     * asks the user input to change the direction to Left, Stay or Right
     */
    public static int[] moveVertically(int x, int y, int moveVal, Boolean isForward) {
        int i = x;
        int j = y;
        while (moveVal > 0) {
            if (isForward) {
                i--;
                moveVal--;
                if (i < 0) {
                    return new int[]{-1, j};
                }
                if (!isBoundaryCaseAfterMove(i, j) && validateFenceAndPlayer(i, j)) {
                    b.addTempPlayerToPosition(i + 1, j);
                    String userInput = queryString(j);
                    b.removePlayerFromPosition(i + 1, j);
                    if (userInput.equalsIgnoreCase("S")) {
                        i++;
                        break;
                    }
                    Boolean isRight = userInput.equalsIgnoreCase("R");
                    i++;
                    moveVal++;
                    return moveHorizontally(i, j, moveVal, isRight);
                }
            } else {
                i++;
                moveVal--;
                if (!isBoundaryCaseAfterMove(i, j) && validateFenceAndPlayer(i, j)) {
                    b.addTempPlayerToPosition(i - 1, j);
                    String userInput = queryString(j);
                    b.removePlayerFromPosition(i - 1, j);
                    if (userInput.equalsIgnoreCase("S")) {
                        i--;
                        break;
                    }
                    Boolean isRight = userInput.equalsIgnoreCase(
                            "R");
                    i--;
                    moveVal++;
                    return moveHorizontally(i, j, moveVal, isRight);
                }
            }
        }
        return new int[]{i, j};
    }

    /**
     * @param col
     * @return
     * Constructs a string to enable buttons for user by validating the boundary cases
     */
    public static String queryString(int col) {
        String userQues = "S";
        if (col != 0 && col != b.getColumns() - 1) {
            userQues += "/L/R";
        } else {
            if (col == 0) {
                userQues += "/R";
            }
            if (col == b.getColumns() - 1) {
                userQues += "/L";
            }
        }
        gvc.enableButtonsForMovement(userQues);
        String userInput = gvc.playerMoveResponse();
        return userInput;
    }


    /**
     * @param x
     * @param y
     * @return
     * True if it hits the boundary after move else returns false
     */
    public static boolean isBoundaryCaseAfterMove(int x, int y) {
        int bx = b.getRows();
        int by = b.getColumns();

        if (y < 0 || y >= by || x >= bx) {
            return true;
        }
        return false;
    }


    /**
     * @param x
     * @param y
     * @param moveVal
     * @param dirVal
     * @return
     * @throws InvalidMoveException
     * Moves the player based on the direction value provided
     * Shall return the end value in case of crossing the boundary of board
     */
    public static int[] move(int x, int y, int moveVal, Directions dirVal) throws InvalidMoveException {
        int tempx, tempy;
        int temp[];
        int bx = b.getRows();
        int by = b.getColumns();
        switch (dirVal) {
            case FORWARD:
                temp = moveVertically(x, y, moveVal, true);
                tempx = temp[0];
                tempy = temp[1];
                break;
            case BACKWARD:
                temp = moveVertically(x, y, moveVal, false);
                tempx = temp[0];
                tempy = temp[1];
                break;
            case MISS_A_TURN:
                tempx = x;
                tempy = y;
                break;
            default:
                throw new InvalidMoveException("Invalid move direction value");
        }
        if (tempx < 0) {
            return new int[]{0, tempy};
        } else if (tempx >= bx) {
            return new int[]{bx - 1, tempy};
        }
        return new int[]{tempx, tempy};
    }
}

