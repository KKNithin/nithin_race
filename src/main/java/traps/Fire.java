package traps;

import static utilities.Constants.GridOperationtype.*;
import static utilities.Constants.TrapType.FIRE;
import core.Board;
import core.Player;
import fx.GameViewController;
import utilities.Constants;

public class Fire implements Trap{


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
