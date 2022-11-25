package traps;

import core.Board;
import core.Player;
import fx.GameViewController;

public interface Trap {

    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer,
                               Player initialPlayer);
}
