package utilities;

import java.util.ArrayList;
import java.util.Random;

import static utilities.Constants.TrapType.*;
import static utilities.Constants.selectedDifficulty;
import core.Board;

public class InitialTrapPlacementService {


    public static void placeObstacles(Board b) {
        Constants.TrapType[] traps = new Constants.TrapType[]{FENCE, FIRE, TELEPROTATION_TUNNEL, TAR_PIT};
        Integer trapsCount = traps.length - 1;
        Random generate = new Random();
        Integer row = Constants.rows - 2;
        Integer col = Constants.columns;
        Integer noOfObstacles = (row * col) / 6;
        if (selectedDifficulty.equalsIgnoreCase("Medium")) {
            noOfObstacles = (row * col) / 5;
        } else if (selectedDifficulty.equalsIgnoreCase("Difficult")) {
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
            Integer i = generate.nextInt(eligiblePositions.size());
            ArrayList<String> colEligiblePos = eligiblePositions.get(i);
            if (colEligiblePos.size() > 0 && i != 0) {
                Integer colRand = generate.nextInt(colEligiblePos.size());
                String j = colEligiblePos.get(colRand);
                b.setTrap(i, Integer.valueOf(j), traps[trapsCount--]);
                noOfObstacles--;
                colEligiblePos.remove(j);
            }
        }
    }
}
