package core;

import static utilities.Constants.GridOperationtype.*;
import traps.*;
import utilities.Constants.TrapType;

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
     * @return length of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows
     * For setting row length on board
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return length of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * @param columns
     * For setting column length on board
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
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
     * @param x Row positon
     * @param y Column position
     * removes player in the given position
     * Also removes player from the UI Grid
     */
    public void removePlayerFromPosition(int x, int y) {
        this.board[x][y].removePlayer();
        StartGame.gvc.modifyGridTile(CLEAR, x, y, null, null);
    }

    /**
     * @param x Row position
     * @param y Column position
     * @param p player object to add
     * Adds player to the given position
     * Also adds player to the UI Grid
     */
    public void addPlayerToPosition(int x, int y, Player p) {
        this.getPosition(x, y).addPlayer(p);
        StartGame.gvc.modifyGridTile(ADD_PLAYER, x, y, p, null);
    }

    /**
     * @param x Row position
     * @param y  position
     * Adds a temporary player in running position on to the UI Grid
     */
    public void addTempPlayerToPosition(int x, int y) {
        StartGame.gvc.modifyGridTile(ADD_TEMP_PLAYER, x, y, null, null);
    }

    /**
     * @param x Row position
     * @param y Column position
     * @param trapType type of trap to be set
     * Sets Trap on the board position and UI Grid
     */
    public void setTrap(int x, int y, TrapType trapType) {
        this.getPosition(x, y).setTrap(createTrap(trapType), trapType);
        StartGame.gvc.modifyGridTile(ADD_OBSTACLE, x, y, null, trapType);
    }

    /**
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
     * @param x Row position
     * @param y Column position
     * @return Type of Trap
     * Returns the type of Trap in given position, if there is no trap it returns None
     */
    public TrapType getTrapType(int x, int y) {
        if(null == this.board[x][y]){
            this.board[x][y] = new Position();
        }
        return  this.getPosition(x, y).getTrapType();
    }

    /**
     * @param trapType type of trap to create
     * @return Trap
     * Creates new Trap object for the given trap type
     */
    public Trap createTrap(TrapType trapType) {
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
