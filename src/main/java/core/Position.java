package core;

import traps.None;
import traps.Trap;
import utilities.Constants.TrapType;

public class Position {
    Player player = new Player();

    Trap trap;

    TrapType trapType;

    public Position() {
    }

    public Trap getTrap() {
        if (null == trap) {
            trap = new None();
        }
        return trap;
    }

    public void setTrap(Trap trap, TrapType trapType) {
        this.trap = trap;
        this.trapType = trapType;
    }

    public TrapType getTrapType() {
        if (null == trapType) {
            return TrapType.NONE;
        }
        return trapType;
    }

    public void setTrapType(TrapType trapType) {
        this.trapType = trapType;
    }

    public void addPlayer(Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        if(this.player.getName() == null) {
            return null;
        }
        return player;
    }

    public void removePlayer() {
        this.player = new Player();
    }

}
