package models.traps;

import models.core.Board;
import models.core.Player;
import fx.controllers.GameViewController;
import models.utilities.Constants;

/**
 * @author Nithin
 * Fire obstacle implementation
 */
public class Fire implements Trap{


    /**
     * Applies Fire trap rules
     * @param b             Board
     * @param gvc           GameViewController
     * @param lastX         Previous row position of player
     * @param lastY         Previous column position of player
     * @param currentPlayer Current player object
     * @param initialPlayer Initial player object
     */
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer,
                               Player initialPlayer){
        int x = currentPlayer.getPositionX();
        int y = currentPlayer.getPositionY();
        System.out.println("##You have entered Fire Trap, Begin Again!!!##");
        b.removePlayerFromPosition(x, y);
        gvc.modifyGridTile(Constants.GridOperationtype.ADD_OBSTACLE, x, y, null, Constants.TrapType.FIRE, "Fire Trap, Begin Again!!");
        currentPlayer.setPosition(initialPlayer.getPositionX(), initialPlayer.getPositionY());
        b.addPlayerToPosition(initialPlayer.getPositionX(), initialPlayer.getPositionY(), currentPlayer);
    }
}
