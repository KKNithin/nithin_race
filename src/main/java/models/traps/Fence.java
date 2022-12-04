package models.traps;

import models.core.Board;
import models.core.Player;
import fx.controllers.GameViewController;

/**
 * @author Nithin
 * Fence obstacle class
 */
public class Fence implements Trap{
    /**
     * Shall apply the rules for the trap
     * @param b Board
     * @param gvc GameViewController
     * @param lastX Previous row position of player
     * @param lastY Previous column position of player
     * @param currentPlayer Current player object
     * @param initialPlayer Initial player object
     */
    @Override
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer, Player initialPlayer) {

    }
}
