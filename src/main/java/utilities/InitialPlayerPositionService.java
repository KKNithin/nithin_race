package utilities;

import java.util.ArrayList;
import java.util.Random;

import static utilities.Constants.initialScore;
import static utilities.Constants.playerColors;
import core.Player;

/**
 * @author Nithin
 * Used to decide all players initial positions
 */
public class InitialPlayerPositionService {
    /**
     * Assigns initial position and color to each player
     * <p>
     *     It chooses the color and column for each player.
     * </p>
     */
    public static void placePlayers() {
        Random generate = new Random();
        ArrayList<String> eligiblePositions = new ArrayList<>();
        int bottomRow = Constants.rows - 1;
        int col = Constants.columns;
        for (int i = 0; i < col; i++) {
            eligiblePositions.add(String.valueOf(i));
        }
        for (String name :
                Constants.playerNames) {
            Integer size = eligiblePositions.size();
            String y = null;
            if (size > 0) {
                Integer rand = generate.nextInt(size);
                y = eligiblePositions.get(rand);

            } else {
                y = eligiblePositions.get(0);
            }
            Constants.allPlayers.add(new Player(bottomRow, Integer.valueOf(y), name, initialScore,
                    playerColors[Integer.valueOf(y)]));
            Constants.playersInitialPos.add(new Player(bottomRow, Integer.valueOf(y), name, initialScore,
                    playerColors[Integer.valueOf(y)]));
            eligiblePositions.remove(y);
        }
    }
}
