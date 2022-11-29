package traps;

import core.Board;
import core.Player;
import fx.GameViewController;

/**
 * @author Nithin
 * Trap Interface
 */
public interface Trap {

    /**
     * @param b Board
     * @param gvc GameViewController
     * @param lastX Previous row position of player
     * @param lastY Previous column position of player
     * @param currentPlayer Current player object
     * @param initialPlayer Initial player object
     */
    void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer,
                               Player initialPlayer);
}
