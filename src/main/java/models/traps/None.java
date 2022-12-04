package models.traps;

import models.core.Board;
import models.core.Player;
import fx.controllers.GameViewController;

/**
 * @author Nithin
 * None obstacle implementation
 */
public class None implements Trap{


    /**
     * Applies trap rules
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
