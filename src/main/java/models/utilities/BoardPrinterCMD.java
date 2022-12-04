package models.utilities;

import models.core.StartGame;

/**
 * @author Nithin
 * Has methods to print board, which helps to debug
 */
public class BoardPrinterCMD {

    /**
     * prints the board on command prompt
     */
    public static void printBoard() {
        for (int i = 0; i < StartGame.board.getRows(); i++) {
            for (int j = 0; j < StartGame.board.getColumns(); j++) {
                printTile(i, j);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints tile
     * @param i Row position
     * @param j Column position
     * Prints each tile of board with required identification
     */
    public static void printTile(int i, int j) {
        if (StartGame.board.getPosition(i, j).getTrapType() == Constants.TrapType.FENCE) {
            System.out.print("P");
        } else if (StartGame.board.getPosition(i, j).getTrapType() == Constants.TrapType.TAR_PIT) {
            System.out.print("T");
        } else if (StartGame.board.getPosition(i, j).getTrapType() == Constants.TrapType.TELEPROTATION_TUNNEL) {
            System.out.print("U");
        } else if (StartGame.board.getPosition(i, j).getTrapType() == Constants.TrapType.FIRE) {
            System.out.print("F");
        }
        if (StartGame.board.getPosition(i, j).getPlayer() == null && StartGame.board.getPosition(i, j).getTrapType() == Constants.TrapType.NONE) {
            System.out.print("_");
        } else if (StartGame.board.getPosition(i, j).getPlayer() != null) {
            char c = StartGame.board.getPosition(i, j).getPlayer().getName().toCharArray()[0];
            System.out.print(c);
        }

    }
}
