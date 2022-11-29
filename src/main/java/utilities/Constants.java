package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.Player;

/**
 * @author Nithin
 * All the common constants used in this game
 */
public class Constants {
    /**
     * Enum of possible directions
     * {@link #FORWARD}
     * {@link #BACKWARD}
     * {@link #MISS_A_TURN}
     */
    public enum Directions {
        /**
         * Move Forward
         */
        FORWARD,
        /**
         * Move Backward
         */
        BACKWARD,
        /**
         * Miss a turn
         */
        MISS_A_TURN
    }

    /**
     * Enum of Trap types
     * {@link #FENCE}
     * {@link #FIRE}
     * {@link #TELEPROTATION_TUNNEL}
     * {@link #TAR_PIT}
     * {@link #NONE}
     */
    public enum TrapType {
        /**
         * Player cannot cross it, must change direction to move
         */
        FENCE,
        /**
         * Player falling on fire shall start from begining
         */
        FIRE,
        /**
         * Player shall choose any other player for him to teleport
         */
        TELEPROTATION_TUNNEL,
        /**
         * Player falling on Tar Pit shall miss his next turn
         */
        TAR_PIT,
        /**
         * Dummy Trap used for coding purpose
         */
        NONE
    }

    /**
     * Enum of possible operations on grid tile modification
     * {@link #ADD_PLAYER}
     * {@link #ADD_OBSTACLE}
     * {@link #PLAYER_IN_TAR}
     * {@link #ADD_TEMP_PLAYER}
     * {@link #CLEAR}
     */
    public enum GridOperationtype {
        /**
         * Removes any existing obstacle and adds the player.
         */
        ADD_PLAYER,
        /**
         * Removes existing player and adds an obstacle.
         */
        ADD_OBSTACLE,
        /**
         * Replaces the Tar pit with an image of player in tar.
         */
        PLAYER_IN_TAR,
        /**
         * Player when obstructed shall be shown in running position to change the direction.
         */
        ADD_TEMP_PLAYER,
        /**
         * Shall clear the tile with any Obstacle/Player.
         */
        CLEAR
    }

    /**
     * Level of difficulty choose before start of game
     * Default difficulty leve shall be "EASY"
     */
    public static String difficultyLevel = "Easy";
    /**
     * Number of required rows on game board
     */
    public static int rows;

    /**
     * Number of required columns on game board
     */
    public static int columns;
    /**
     * Number of players required in game
     */
    public static int numberOfPlayers;
    /**
     * Custom tile height based on the screen size
     */
    public static double reqTileHeight;
    /**
     * Custom tile width based on the screen size
     */
    public static double reqTileWidth;
    /**
     * All the player names
     */
    public static ArrayList<String> playerNames = new ArrayList<>();
    /**
     * All player objects
     */
    public static List<Player> allPlayers = new ArrayList<>();
    /**
     * All players initial positions
     */
    public static List<Player> playersInitialPos = new ArrayList<>();
    /**
     * Map of obstacles and image file path URIs
     */
    public static HashMap<TrapType, String> obstacleImages = new HashMap<>();
    /**
     * Actual screen width
     */
    public static double screenWidth;
    /**
     * Actual screen height
     */
    public static double screenHeight;

    /**
     * Player color array
     */
    public static String[] playerColors = new String[]{"black", "blue-d", "yellow", "blue-l", "brown", "gray-d", "red",
            "gray-l", "green-d", "orange", "green-l", "maroon", "pink-d", "violet-d", "pink-l"};

    /**
     * File path used for storing the top 10 player score details
     */
    public static String topScoresFile = "src/main/resources/top_scores.ser";

    /**
     * Initial score for each player
     */
    public static int initialScore = 50;
}
