package core;

import java.io.Serializable;
import java.util.List;

public class TopScores implements Serializable {
    List<Player> playerList;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
