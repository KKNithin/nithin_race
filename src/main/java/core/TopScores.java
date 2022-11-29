package core;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nithin
 * Shall have list of top score players used to save in a flat file
 */
public class TopScores implements Serializable {
    /**
     * Array list of players
     */
    private List<Player> playerList;

    /**
     * @return list of players.
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * @param playerList list of players will be updated
     */
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
