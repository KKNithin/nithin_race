package utilities;

import java.util.Random;

/**
 * @author Nithin
 * Dice rolled to get the value for the player to move.
 */
public class MoveDice {
    /**
     * Array of possible faces of direction on dice
     */
    private static final int[] values = {1, 2, 3, 4};
    /**
     * Random number generator object
     */
    private static Random generate = new Random();

    /**
     * @return the number of places based on the random number generated
     */
    public static int rollDice() {
        return values[generate.nextInt(4)];
    }
}
