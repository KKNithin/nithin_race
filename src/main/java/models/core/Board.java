package models.core;

import models.traps.*;
import models.utilities.Constants;

/**
 * @author Nithin
 * Broad class shall hold the size of rows and columns.
 * It holds a position matrix of game board.
 */
public class Board {

    /**
     * Row length of board
     */
    private int rows;
    /**
     * Column length of board
     */
    private int columns;
    /**
     * 2D matrix of Position objects
     */
    private Position[][] board;

    /**
     * Board Constructor
     * @param rows number of required rows on board
     * @param columns number of required columns on board
     * Board constructor which take rows and columns
     */
    public Board(int rows, int columns) {
        this.board = new Position[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Gets number of rows
     * @return length of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets number of rows
     * @param rows
     * For setting row length on board
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Gives the number of columns
     * @return length of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets the number of columns
     * @param columns
     * For setting column length on board
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Gives the position
     * @param x Row position
     * @param y Column position
     * @return position on board
     */
    public Position getPosition(int x, int y) {
        if(this.board[x][y] == null) {
            this.board[x][y] = new Position();
        }
        return this.board[x][y];
    }

    /**
     * Removed a player from position
     * @param x Row positon
     * @param y Column position
     * removes player in the given position
     * Also removes player from the UI Grid
     */
    public void removePlayerFromPosition(int x, int y) {
        this.board[x][y].removePlayer();
        StartGame.gameViewController.modifyGridTile(Constants.GridOperationtype.CLEAR, x, y, null, null);
    }

    /**
     * Adds player to a position
     * @param x Row position
     * @param y Column position
     * @param p player object to add
     * Adds player to the given position
     * Also adds player to the UI Grid
     */
    public void addPlayerToPosition(int x, int y, Player p) {
        this.getPosition(x, y).addPlayer(p);
        StartGame.gameViewController.modifyGridTile(Constants.GridOperationtype.ADD_PLAYER, x, y, p, null);
    }

    /**
     * Adds player to position
     * @param x Row position
     * @param y  position
     * Adds a temporary player in running position on to the UI Grid
     */
    public void addTempPlayerToPosition(int x, int y) {
        StartGame.gameViewController.modifyGridTile(Constants.GridOperationtype.ADD_TEMP_PLAYER, x, y, null, null);
    }

    /**
     * Sets trap on board
     * @param x Row position
     * @param y Column position
     * @param trapType type of trap to be set
     * Sets Trap on the board position and UI Grid
     */
    public void setTrap(int x, int y, Constants.TrapType trapType) {
        this.getPosition(x, y).setTrap(createTrap(trapType), trapType);
        StartGame.gameViewController.modifyGridTile(Constants.GridOperationtype.ADD_OBSTACLE, x, y, null, trapType);
    }

    /**
     * Gets the trap
     * @param x Row position
     * @param y Column position
     * @return Trap Returns Trap in the given position, if there is no trap it returns None
     */
    public Trap getTrap(int x, int y) {
        if(null == this.board[x][y]){
            this.board[x][y] = new Position();
        }
        return this.getPosition(x, y).getTrap();
    }

    /**
     * Gets the type of trap
     * @param x Row position
     * @param y Column position
     * @return Type of Trap
     * Returns the type of Trap in given position, if there is no trap it returns None
     */
    public Constants.TrapType getTrapType(int x, int y) {
        if(null == this.board[x][y]){
            this.board[x][y] = new Position();
        }
        return  this.getPosition(x, y).getTrapType();
    }

    /**
     * Creates a trap on board
     * @param trapType type of trap to create
     * @return Trap
     * Creates new Trap object for the given trap type
     */
    public Trap createTrap(Constants.TrapType trapType) {
        Trap trap;
        switch (trapType) {
            case FIRE:
                trap = new Fire();
                break;
            case TELEPROTATION_TUNNEL:
                trap = new TeleportationTunnel();
                break;
            case TAR_PIT:
                trap = new TarPit();
                break;
            case FENCE:
                trap = new Fence();
                break;
            default:
                trap = null;
                break;
        }
        return trap;
    }
}
