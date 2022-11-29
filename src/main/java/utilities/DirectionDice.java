package utilities;

import java.util.Random;

import static utilities.Constants.Directions.*;

/**
 * @author Nithin
 * Dice rolled to get the direction value for the player to move.
 */
public class DirectionDice {

    /**
     * Array of possible faces of direction on dice
     */
    private static final Constants.Directions[] values = {FORWARD, FORWARD,
            BACKWARD, MISS_A_TURN};
    /**
     * Random number generator object
     */
    private static Random generate = new Random();

    /**
     * @return the direction based on the random number generated
     */
    public static Constants.Directions rollDice() {
        return values[generate.nextInt(values.length)];
    }
}
