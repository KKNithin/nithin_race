package traps;

import static utilities.Constants.GridOperationtype.PLAYER_IN_TAR;
import static utilities.Constants.TrapType.TAR_PIT;
import core.Board;
import core.Player;
import core.StartGame;
import fx.GameViewController;

public class TarPit implements Trap{


    @Override
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer, Player initialPlayer) {
        int x = currentPlayer.getPositionX();
        int y = currentPlayer.getPositionY();
        System.out.println("##You have entered a Tar Pit##");
        System.out.println("##You Missed your next chance!!!##");
        StartGame.playersInTarPit.put(currentPlayer, 1);
        gvc.modifyGridTile(PLAYER_IN_TAR, x, y, null, TAR_PIT, "You have entered a Tar Pit");
    }
}
