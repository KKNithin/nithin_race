package models.core;

import java.util.HashMap;
import java.util.Map;

import static models.utilities.Constants.*;
import static models.utilities.Constants.GridOperationtype.ADD_OBSTACLE;
import static models.utilities.Constants.GridOperationtype.ADD_PLAYER;
import static models.utilities.Constants.TrapType.*;
import models.exceptions.BoundaryCaseException;
import models.exceptions.InvalidMoveException;
import fx.controllers.GameViewController;
import models.utilities.BoardPrinterCMD;
import models.utilities.InitialPlayerPositionService;
import models.utilities.InitialTrapPlacementService;

/**
 * @author Nithin
 * Start Game is the main class for the actual game logic
 */
public class StartGame {
    /**
     * Board object used in the entire game
     */
    public static Board board;
    /**
     * List to hold players and their number of times to be in pit
     */
    public static Map<Player, Integer> playersInTarPit = new HashMap<>();
    /**
     * Java FX view controller from main game screen
     */
    public static GameViewController gameViewController;

    /**
     * Start point of actual backend game
     * @param gameViewController Game controller of Java Fx
     * Main method is called by the Java FX to initialize the backend
     */
    public static void main(GameViewController gameViewController) {
        StartGame.gameViewController = gameViewController;
        board = new Board(rows, columns);
        InitialPlayerPositionService.placePlayers();
        for (Player p : allPlayers) {
            board.addPlayerToPosition(p.getPositionX(), p.getPositionY(), p);
        }
        InitialTrapPlacementService.placeObstacles();
        BoardPrinterCMD.printBoard();
    }


    /**
     * Checks players eligibility
     * @param p - player object to check the eligibility
     * @return Boolean - based on the eligibility
     * <p>
     *      Shall check the eligibility of player
     *      False if he is in pit and the count is not 0
     *      True if he is not in pit
     * </p>
     *
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
     * Validates the move of a player
     * @param p Player object
     * @param moveRollVal Move value of dice
     * @param dirRollVal Direction value of dice
     * @return Boolean True if the move is valid and done, else returns false
     * @throws BoundaryCaseException
     * Validates the roll, moves the player and calculates the score for it
     */
    public static boolean validateMoveRoll(Player p, int moveRollVal, Directions dirRollVal) throws BoundaryCaseException {
        int x = p.getPositionX();
        int y = p.getPositionY();
        int pointsGained = moveRollVal * 10;
        int[] tempXY = new int[2];
        int tempX, tempY;
        board.removePlayerFromPosition(x, y);
        if (board.getPosition(x, y).getTrapType() == TAR_PIT) {
            gameViewController.modifyGridTile(ADD_OBSTACLE, p.getPositionX(), p.getPositionY(), null, TAR_PIT);
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
        board.addPlayerToPosition(tempX, tempY, p);
        gameViewController.modifyGridTile(ADD_PLAYER, tempX, tempY, p, NONE);
        if (board.getTrapType(p.getPositionX(), p.getPositionY()) != NONE) {
            int currentPlayerIndex = playersInitialPos.indexOf(p);
            if (currentPlayerIndex > -1) {
                Player initialPlayer = playersInitialPos.get(currentPlayerIndex);
                board.getTrap(p.getPositionX(), p.getPositionY()).applyTrapRules(board, gameViewController, x, y, p, initialPlayer);
            }
        }
        return true;
    }

    /**
     * Moves player horizontally
     * @param x row value of position
     * @param y column value of position
     * @param moveVal remaining move value of player
     * @param isRight if the value is true player shall move right else he moves left
     * @return IntegerArray An array of row and column position
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
                    board.addTempPlayerToPosition(i, j - 1);
                    gameViewController.enableButtonsForMovement("U/D/S");
                    String userString = gameViewController.playerMoveResponse();
                    board.removePlayerFromPosition(i, j - 1);
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
                    board.addTempPlayerToPosition(i, j + 1);
                    gameViewController.enableButtonsForMovement("U/D/S");
                    String userString = gameViewController.playerMoveResponse();
                    board.removePlayerFromPosition(i, j + 1);
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
     * Checks for fence and player
     * @param i Row position
     * @param j Column position
     * @return Boolean
     * Validates the position with Fence or player
     * if so returns true else returns false
     */
    public static Boolean validateFenceAndPlayer(int i, int j) {
        return (board.getTrapType(i, j) == FENCE) || (board.getPosition(i, j).getPlayer() != null);
    }

    /**
     * Moves player vertically
     * @param x row value of position
     * @param y column value of position
     * @param moveVal remaining move value of player
     * @param isForward if the value is true player shall move forward else he moves backward
     * @return IntegerArray An integer array of x and y
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
                    board.addTempPlayerToPosition(i + 1, j);
                    String userInput = queryString(j);
                    board.removePlayerFromPosition(i + 1, j);
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
                    board.addTempPlayerToPosition(i - 1, j);
                    String userInput = queryString(j);
                    board.removePlayerFromPosition(i - 1, j);
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
     * Gives a string
     * @param col Column position
     * @return String query string
     * Constructs a string to enable buttons for user by validating the boundary cases
     */
    public static String queryString(int col) {
        String userQues = "S";
        if (col != 0 && col != board.getColumns() - 1) {
            userQues += "/L/R";
        } else {
            if (col == 0) {
                userQues += "/R";
            }
            if (col == board.getColumns() - 1) {
                userQues += "/L";
            }
        }
        gameViewController.enableButtonsForMovement(userQues);
        String userInput = gameViewController.playerMoveResponse();
        return userInput;
    }


    /**
     * Checks for boundary case
     * @param x Row position
     * @param y Column position
     * @return Boolean
     * True if it hits the boundary after move else returns false
     */
    public static boolean isBoundaryCaseAfterMove(int x, int y) {
        int bx = board.getRows();
        int by = board.getColumns();

        if (y < 0 || y >= by || x >= bx) {
            return true;
        }
        return false;
    }


    /**
     * Moves the player
     * @param x Row position
     * @param y Column position
     * @param moveVal Move value of dice
     * @param dirVal Direction value of dice
     * @return IntegerArray Array of row and column position
     * @throws InvalidMoveException
     * Moves the player based on the direction value provided
     * Shall return the end value in case of crossing the boundary of board
     */
    public static int[] move(int x, int y, int moveVal, Directions dirVal) throws InvalidMoveException {
        int tempx, tempy;
        int temp[];
        int bx = board.getRows();
        int by = board.getColumns();
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

