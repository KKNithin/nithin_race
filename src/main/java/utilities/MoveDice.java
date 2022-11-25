package utilities;

import java.util.Random;

public class MoveDice {
    private static final int[] values = {1, 2, 3, 4};
    private static Random generate = new Random();

    public static int rollDice() {
        return values[generate.nextInt(4)];
    }
}
