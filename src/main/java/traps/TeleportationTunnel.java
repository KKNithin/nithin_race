package traps;

import java.util.ArrayList;
import java.util.List;

import static utilities.Constants.GridOperationtype.*;
import static utilities.Constants.GridOperationtype.ADD_PLAYER;
import static utilities.Constants.TrapType.TELEPROTATION_TUNNEL;
import static utilities.Constants.allPlayers;
import core.Board;
import core.Player;
import core.StartGame;
import fx.GameViewController;

public class TeleportationTunnel implements Trap{

    @Override
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer, Player initialPlayer) {
        int x = currentPlayer.getPositionX();
        int y = currentPlayer.getPositionY();
        b.removePlayerFromPosition(x, y);
        gvc.modifyGridTile(ADD_OBSTACLE, x, y, null, TELEPROTATION_TUNNEL, "You have entered a Teleportation Tunnel");
        System.out.println("##You have entered a Teleportation Tunnel##");
        while (true) {
            String name = gvc.showTeleportationDialog(getPlayerForTeleportation(currentPlayer.getName()));
            Player selectedPlayer = getPlayer(name);
            if (selectedPlayer != null) {
                int sX = selectedPlayer.getPositionX();
                int sY = selectedPlayer.getPositionY();
                b.removePlayerFromPosition(sX, sY);
                currentPlayer.setPosition(sX, sY);
                b.addPlayerToPosition(sX, sY, currentPlayer);
                selectedPlayer.setPosition(lastX, lastY);
                b.addPlayerToPosition(lastX, lastY, selectedPlayer);
                break;
            }
        }
    }
    public  List<String> getPlayerForTeleportation(String name) {
        List<String> playersForTeleportation = new ArrayList<>();
        for (Player p : allPlayers) {
            if (!p.getName().equals(name)) {
                playersForTeleportation.add(p.getName());
            }
        }
        return playersForTeleportation;
    }
    public Player getPlayer(String name) {

        for (Player p : allPlayers) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
