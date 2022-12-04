package models.traps;

import models.core.Board;
import models.core.Player;
import models.core.StartGame;
import fx.controllers.GameViewController;
import models.utilities.Constants;

/**
 * @author Nithin
 * Tar Piy obstacle implementation.
 */
public class TarPit implements Trap{


    /**
     * Applies Tar pit trap rules
     * @param b             Board
     * @param gvc           GameViewController
     * @param lastX         Previous row position of player
     * @param lastY         Previous column position of player
     * @param currentPlayer Current player object
     * @param initialPlayer Initial player object
     */
    @Override
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer, Player initialPlayer) {
        int x = currentPlayer.getPositionX();
        int y = currentPlayer.getPositionY();
        System.out.println("##You have entered a Tar Pit##");
        System.out.println("##You Missed your next chance!!!##");
        StartGame.playersInTarPit.put(currentPlayer, 1);
        gvc.modifyGridTile(Constants.GridOperationtype.PLAYER_IN_TAR, x, y, null, Constants.TrapType.TAR_PIT, "You have entered a Tar Pit");
    }
}
