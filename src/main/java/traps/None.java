package traps;

import core.Board;
import core.Player;
import fx.GameViewController;
import utilities.Constants;

public class None implements Trap{

    private Constants.TrapType trapType;

    @Override
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer, Player initialPlayer) {

    }
}
