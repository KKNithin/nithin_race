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
     */
    public enum Directions {FORWARD, BACKWARD, MISS_A_TURN};

    /**
     * Enum of Trap types
     * Fence: Player cannot cross it, must change direction to move
     * Fire: Player falling on fire shall start from begining
     * Teleportation Tunnel: Player shall choose any other player for him to teleport
     * Tar Pit: Player falling on Tar Pit shall miss his next turn
     * None: Dummy Trap used for coding purpose
     */
    public enum TrapType {FENCE, FIRE, TELEPROTATION_TUNNEL, TAR_PIT ,NONE};

    /**
     * Enum of possible operations on grid tile modification
     * Add player: Removes any existing obstacle and adds the player.
     * Add Obstacle: Removes existing player and adds an obstacle
     * Player in Tar: Replaces the Tar pit with an image of player in tar
     * Add Temp Player: Player when obstructed shall be shown in running position to change the direction
     * Clear: Shall clear the tile with any Obstacle/Player
     */
    public enum GridOperationtype {ADD_PLAYER, ADD_OBSTACLE, PLAYER_IN_TAR, ADD_TEMP_PLAYER, CLEAR}

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
}
