package models.utilities;

import java.util.ArrayList;
import java.util.Random;

import models.core.StartGame;

/**
 * @author Nithin
 * Used to place the obstacles
 */
public class InitialTrapPlacementService {


    /**
     * Method which places the obstacles on board
     * <p>
     *     It decides the total number of obstacles needed for the game based
     *     on difficulty level.
     *     It Chooses a random row and column to place the obstacle
     * </p>
     */
    public static void placeObstacles() {
        Constants.TrapType[] traps = new Constants.TrapType[]{Constants.TrapType.FENCE, Constants.TrapType.FIRE, Constants.TrapType.TELEPROTATION_TUNNEL, Constants.TrapType.TAR_PIT};
        int trapsCount = traps.length - 1;
        Random generate = new Random();
        int row = Constants.rows - 2;
        int col = Constants.columns;
        int noOfObstacles = (row * col) / 6;
        if (Constants.difficultyLevel.equalsIgnoreCase("Medium")) {
            noOfObstacles = (row * col) / 5;
        } else if (Constants.difficultyLevel.equalsIgnoreCase("Difficult")) {
            noOfObstacles = (row * col) / 4;
        }
        ArrayList<ArrayList<String>> eligiblePositions = new ArrayList<>();
        for (int x = 0; x < row; x++) {
            eligiblePositions.add(new ArrayList<>());
            ArrayList<String> rowList = eligiblePositions.get(x);
            for (int y = 0; y < col; y++) {
                rowList.add(String.valueOf(y));
            }
        }
        while (noOfObstacles > 0) {
            trapsCount = trapsCount < 0 ? traps.length - 1 : trapsCount;
            int i = generate.nextInt(eligiblePositions.size());
            ArrayList<String> colEligiblePos = eligiblePositions.get(i);
            if (colEligiblePos.size() > 0 && i != 0) {
                int colRand = generate.nextInt(colEligiblePos.size());
                String j = colEligiblePos.get(colRand);
                StartGame.board.setTrap(i, Integer.valueOf(j), traps[trapsCount--]);
                noOfObstacles--;
                colEligiblePos.remove(j);
            }
        }
    }
}
