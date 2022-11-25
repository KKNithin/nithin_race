package utilities;

import java.util.ArrayList;
import java.util.Random;

import static utilities.Constants.playerColors;
import core.Player;
import exceptions.PlayerNameError;

public class InitialPlayerPositionService {
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
            try {
                Integer size = eligiblePositions.size();
                String y = null;
                if (size > 0) {
                    Integer rand = generate.nextInt(size);
                    y = eligiblePositions.get(rand);

                } else {
                    y = eligiblePositions.get(0);
                }
                Constants.allPlayers.add(new Player(bottomRow, Integer.valueOf(y), name, 50, playerColors[Integer.valueOf(y)]));
                Constants.playersInitialPos.add(new Player(bottomRow, Integer.valueOf(y), name, 50,
                        playerColors[Integer.valueOf(y)]));
                eligiblePositions.remove(y);
            } catch (PlayerNameError e) {
                throw new RuntimeException(e);
            }
        }
    }
}
