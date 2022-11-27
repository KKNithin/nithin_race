package core;

import java.io.*;
import java.util.*;

import static utilities.Constants.*;
import static utilities.Constants.GridOperationtype.*;
import static utilities.Constants.TrapType.*;
import exceptions.BoundaryCaseException;
import exceptions.InvalidMoveException;
import fx.GameViewController;
import utilities.InitialPlayerPositionService;
import utilities.InitialTrapPlacementService;

public class StartGame {
    public static Board b;
    public static Map<Player, Integer> playersInTarPit = new HashMap<>();
    public static GameViewController gvc;
    public static void main(GameViewController gameViewController) {
        gvc = gameViewController;
        b = new Board(rows, columns);
        InitialPlayerPositionService.placePlayers();
        for (Player p : allPlayers) {
            b.addPlayerToPosition(p.getPositionX(), p.getPositionY(), p);
        }
        InitialTrapPlacementService.placeObstacles(b);
        printBoard();
    }

    public static TopScores readTopScoreFile() {
        TopScores topScores = new TopScores();
        try {
            File f = new File("top_scores.ser");
            if(f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                topScores = (TopScores) obj;
                objectIn.close();
            } else {
                f.createNewFile();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Top Scores file Not found");
        }
        return topScores;
    }

    public static void writeWinnerScoreToFile(Player p) throws IOException, ClassNotFoundException {
        List<Player> tempTopScores = new ArrayList<>();
        TopScores topScores = new TopScores();
        topScores.setPlayerList(tempTopScores);

        try {
            File f = new File("top_scores.ser");
            if(f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                topScores = (TopScores) obj;
                objectIn.close();
            } else {
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Top Scores file Not found");
        }


        tempTopScores = topScores.getPlayerList();

        boolean isPlayerAlreadyExists = false;
        for (int i = 0; i < topScores.getPlayerList().size(); i++) {
            if(p.getName().equalsIgnoreCase(tempTopScores.get(i).getName())) {
                if(p.getScore() > tempTopScores.get(i).getScore()) {
                    tempTopScores.get(i).replace(p);
                }
                isPlayerAlreadyExists = true;
            }
        }


        if(tempTopScores.size() == 0 || !isPlayerAlreadyExists) {
            tempTopScores.add(new Player(p.getName(), p.getScore()));
        }
        Collections.sort(tempTopScores);
        if (tempTopScores.size() > 10) {
            tempTopScores.remove(10);
        }
        topScores.setPlayerList(tempTopScores);

        FileOutputStream fileOut = new FileOutputStream("top_scores.ser");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(topScores);
        objectOut.close();
    }

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

    public static boolean validateMoveRoll(Player p, int moveRollVal, Directions dirRollVal) throws BoundaryCaseException {
        int x = p.getPositionX();
        int y = p.getPositionY();
        int pointsGained = moveRollVal * 10;
        int[] tempXY = new int[2];
        int tempX, tempY;
        b.removePlayerFromPosition(x, y);
//        gvc.modifyGridTile(CLEAR, x, y, p, NONE);
        if(b.getPosition(x, y).getTrapType()==TAR_PIT){
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
    public static int[] moveHorizontally(int x, int y, int moveVal, Boolean isRight) {
        int i = x;
        int j = y;
        while (moveVal > 0) {
            if (isRight) {
                j++;
                moveVal--;
                if (isBoundaryCaseAfterMove(i, j) || validateFenceAndPlayer(i,j)) {
                    b.addTempPlayerToPosition(i, j - 1, b.getPosition(i, j).getPlayer());
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
                if (isBoundaryCaseAfterMove(i, j) || validateFenceAndPlayer(i,j)) {
                    b.addTempPlayerToPosition(i, j + 1, b.getPosition(i, j).getPlayer());
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

    public static Boolean validateFenceAndPlayer(int i, int j) {
        return (b.getTrapType(i, j) == FENCE) || (b.getPosition(i, j).getPlayer() != null);
    }

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
                if (!isBoundaryCaseAfterMove(i, j) && validateFenceAndPlayer(i,j)) {
                    b.addTempPlayerToPosition(i + 1, j, b.getPosition(i, j).getPlayer());
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
                if (!isBoundaryCaseAfterMove(i, j) && validateFenceAndPlayer(i,j)) {
                    b.addTempPlayerToPosition(i - 1, j, b.getPosition(i, j).getPlayer());
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


    public static boolean isBoundaryCaseAfterMove(int x, int y) {
        int bx = b.getRows();
        int by = b.getColumns();

        if (y < 0 || y >= by || x >= bx) {
            return true;
        }
        return false;
    }


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
    public static void printBoard() {
        for (int i = 0; i < b.getRows(); i++) {
            for (int j = 0; j < b.getColumns(); j++) {
                printTile(i, j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void printTile(int i, int j) {
        if (b.getPosition(i, j).getTrapType() == FENCE) {
            System.out.print("P");
        } else if (b.getPosition(i, j).getTrapType() == TAR_PIT) {
            System.out.print("T");
        } else if (b.getPosition(i, j).getTrapType() == TELEPROTATION_TUNNEL) {
            System.out.print("U");
        } else if (b.getPosition(i, j).getTrapType() == FIRE) {
            System.out.print("F");
        }
        if (b.getPosition(i, j).getPlayer() == null && b.getPosition(i, j).getTrapType() == NONE) {
            System.out.print("_");
        } else if (b.getPosition(i, j).getPlayer() != null) {
            char c = b.getPosition(i, j).getPlayer().getName().toCharArray()[0];
            System.out.print(c);
        }

    }
}

