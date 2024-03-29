package models.traps;

import java.util.ArrayList;
import java.util.List;

import models.core.Board;
import models.core.Player;
import fx.controllers.GameViewController;
import models.utilities.Constants;

/**
 * @author Nithin
 * Teleportation Tunnel obstacle implementation
 */
public class TeleportationTunnel implements Trap{

    /**
     * Applies Teleportation tunnel trap rules
     * @param b             Board
     * @param gvc           GameViewController
     * @param lastX         Previous row position of player
     * @param lastY         Previous column position of player
     * @param currentPlayer Current player object
     * @param initialPlayer Initial player object
     */
    @Override
    public void applyTrapRules(Board b, GameViewController gvc, int lastX, int lastY, Player currentPlayer, Player initialPlayer) {
        int x = currentPlayer.getPositionX();
        int y = currentPlayer.getPositionY();
        b.removePlayerFromPosition(x, y);
        gvc.modifyGridTile(Constants.GridOperationtype.ADD_OBSTACLE, x, y, null, Constants.TrapType.TELEPROTATION_TUNNEL, "You have entered a Teleportation Tunnel");
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

    /**
     * @param name Player Name
     * @return PlayerList List of all players except the current one
     */
    public  List<String> getPlayerForTeleportation(String name) {
        List<String> playersForTeleportation = new ArrayList<>();
        for (Player p : Constants.allPlayers) {
            if (!p.getName().equals(name)) {
                playersForTeleportation.add(p.getName());
            }
        }
        return playersForTeleportation;
    }

    /**
     * @param name Player name
     * @return Player Current player object
     */
    public Player getPlayer(String name) {

        for (Player p : Constants.allPlayers) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
