package core;

import traps.None;
import traps.Trap;
import utilities.Constants.TrapType;

/**
 * @author Nithin
 * Position class shall contain player, Trap and TrapType
 */
public class Position {
    /**
     * Creating a new player object
     */
    private Player player = new Player();

    /**
     * Trap
     */
    private Trap trap;

    /**
     * Trap type
     */
    private TrapType trapType;

    /**
     * Default position constructor
     */
    public Position() {
    }

    /**
     * @return Trap
     * gets the trap object
     */
    public Trap getTrap() {
        if (null == trap) {
            trap = new None();
        }
        return trap;
    }

    /**
     * @param trap
     * @param trapType
     * sets trap object and trap type
     */
    public void setTrap(Trap trap, TrapType trapType) {
        this.trap = trap;
        this.trapType = trapType;
    }

    /**
     * @return TrapType
     * gets the trap type
     */
    public TrapType getTrapType() {
        if (null == trapType) {
            return TrapType.NONE;
        }
        return trapType;
    }

    /**
     * @param p Player object
     * adds player to position
     */
    public void addPlayer(Player p) {
        this.player = p;
    }

    /**
     * @return Player
     * gets player from position
     */
    public Player getPlayer() {
        if(this.player.getName() == null) {
            return null;
        }
        return player;
    }

    /**
     * Removes player from position
     */
    public void removePlayer() {
        this.player = new Player();
    }

}
