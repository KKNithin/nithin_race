package models.core;

import models.traps.None;
import models.traps.Trap;
import models.utilities.Constants;

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
    private Constants.TrapType trapType;

    /**
     * Default position constructor
     */
    public Position() {
    }

    /**
     * Gets Trap
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
     * Sets the trap
     * @param trap Trap object to be set
     * @param trapType Type of trap to be set
     * sets trap object and trap type
     */
    public void setTrap(Trap trap, Constants.TrapType trapType) {
        this.trap = trap;
        this.trapType = trapType;
    }

    /**
     * Gets type of trap
     * @return TrapType
     * gets the trap type
     */
    public Constants.TrapType getTrapType() {
        if (null == trapType) {
            return Constants.TrapType.NONE;
        }
        return trapType;
    }

    /**
     * Adds player
     * @param p Player object
     * adds player to position
     */
    public void addPlayer(Player p) {
        this.player = p;
    }

    /**
     * Gets player
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
