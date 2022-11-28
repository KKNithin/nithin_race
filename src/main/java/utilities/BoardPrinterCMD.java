package utilities;

import static core.StartGame.b;
import static utilities.Constants.TrapType.*;

public class BoardPrinterCMD {

    public static void printBoard() {
        for (int i = 0; i < b.getRows(); i++) {
            for (int j = 0; j < b.getColumns(); j++) {
                printTile(i, j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void printTile(int i, int j) {
        if (b.getPosition(i, j).getTrapType() == FENCE) {
            System.out.print("P");
        } else if (b.getPosition(i, j).getTrapType() == TAR_PIT) {
            System.out.print("T");
        } else if (b.getPosition(i, j).getTrapType() == TELEPROTATION_TUNNEL) {
            System.out.print("U");
        } else if (b.getPosition(i, j).getTrapType() == FIRE) {
            System.out.print("F");
        }
        if (b.getPosition(i, j).getPlayer() == null && b.getPosition(i, j).getTrapType() == NONE) {
            System.out.print("_");
        } else if (b.getPosition(i, j).getPlayer() != null) {
            char c = b.getPosition(i, j).getPlayer().getName().toCharArray()[0];
            System.out.print(c);
        }

    }
}
