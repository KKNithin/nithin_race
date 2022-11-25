package core;

import static utilities.Constants.GridOperationtype.*;
import static utilities.Constants.TrapType.FIRE;
import fx.GameViewController;
import traps.*;
import utilities.Constants.TrapType;

public class Board {

    private int rows;
    private int columns;
    private Position[][] board;

    public Board(int rows, int columns) {
        this.board = new Position[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Position getPosition(int x, int y) {
        if(this.board[x][y] == null) {
            this.board[x][y] = new Position();
        }
        return this.board[x][y];
    }

    public void setPosition(int x, int y, Position position) {
        this.board[x][y] = position;
    }

    public void removePlayerFromPosition(int x, int y) {
        this.board[x][y].removePlayer();
        StartGame.gvc.modifyGridTile(CLEAR, x, y, null, null);
    }

    public void addPlayerToPosition(int x, int y, Player p) {
        this.getPosition(x, y).addPlayer(p);
        StartGame.gvc.modifyGridTile(ADD_PLAYER, x, y, p, null);
    }

    public void addTempPlayerToPosition(int x, int y, Player p) {
        this.getPosition(x, y).addPlayer(p);
        StartGame.gvc.modifyGridTile(ADD_TEMP_PLAYER, x, y, p, null);
    }
    public void setTrap(int x, int y, TrapType trapType) {
        this.getPosition(x, y).setTrap(createTrap(trapType), trapType);
        StartGame.gvc.modifyGridTile(ADD_OBSTACLE, x, y, null, trapType);
    }

    public Trap getTrap(int x, int y) {
        if(null == this.board[x][y]){
            this.board[x][y] = new Position();
        }
        return this.getPosition(x, y).getTrap();
    }
    public TrapType getTrapType(int x, int y) {
        if(null == this.board[x][y]){
            this.board[x][y] = new Position();
        }
        return  this.getPosition(x, y).getTrapType();
    }

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
