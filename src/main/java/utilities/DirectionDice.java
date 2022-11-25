package utilities;

import java.util.Random;

public class DirectionDice {

    private static final Constants.Directions[] values = {Constants.Directions.FORWARD, Constants.Directions.FORWARD,
            Constants.Directions.BACKWARD, Constants.Directions.MISS_A_TURN};
    private static Random generate = new Random();

    public static Constants.Directions rollDice() {
        return values[generate.nextInt(4)];
    }
}
