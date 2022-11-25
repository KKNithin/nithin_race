package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.Player;

public class Constants {
    public enum Directions {FORWARD, BACKWARD, MISS_A_TURN};
    public enum TrapType {FENCE, FIRE, TELEPROTATION_TUNNEL, TAR_PIT ,NONE};

    public enum GridOperationtype {ADD_PLAYER, ADD_OBSTACLE, PLAYER_IN_TAR, ADD_TEMP_PLAYER, CLEAR}

    public static String selectedDifficulty = "Easy";
    public static int rows;
    public static int columns;
    public static int numberOfPlayers;
    public static double reqTileHeight;
    public static double reqTileWidth;
    public static ArrayList<String> playerNames = new ArrayList<>();
    public static List<Player> allPlayers = new ArrayList<>();
    public static List<Player> playersInitialPos = new ArrayList<>();
    public static HashMap<TrapType, String> obstacleImages = new HashMap<>();
    public static double screenWidth;
    public static double screenHeight;

    public static String[] playerColors = new String[]{"black", "blue-d", "yellow", "blue-l", "brown", "gray-d", "red",
            "gray-l", "green-d", "orange", "green-l", "maroon", "pink-d", "violet-d", "pink-l"};
}
