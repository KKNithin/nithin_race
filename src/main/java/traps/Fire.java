package traps;

import static utilities.Constants.GridOperationtype.*;
import static utilities.Constants.TrapType.FIRE;
import core.Board;
import core.Player;
import fx.GameViewController;
import utilities.Constants;

/**
 * @author Nithin
 * Fire obstacle implementation
 */
public class Fire implements Trap{


    /**
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
        gvc.modifyGridTile(ADD_OBSTACLE, x, y, null, FIRE, "Fire Trap, Begin Again!!");
        currentPlayer.setPosition(initialPlayer.getPositionX(), initialPlayer.getPositionY());
        b.addPlayerToPosition(initialPlayer.getPositionX(), initialPlayer.getPositionY(), currentPlayer);
    }
}
