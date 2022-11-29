package traps;

import core.Board;
import core.Player;
import fx.GameViewController;
import utilities.Constants;

/**
 * @author Nithin
 * None obstacle implementation
 */
public class None implements Trap{


    /**
     * @param b             Board
     * @param gvc           GameViewController
     * @param lastX         Previous row position of player
     * @param lastY         Previous column position of player
     * @param currentPlayer Current player object
     * @param initialPlayer Initial player object
     */
    @Override
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer, Player initialPlayer) {
        //Empty method used for failure case purpose
    }
}
