package models.exceptions;

/**
 * @author Nithin
 * Custom Invalid Move Exception
 */
public class InvalidMoveException extends Exception{
    /**
     * Invalid move exception constructor
     * @param msg message to print
     */
    public InvalidMoveException(String msg) {
        super(msg);
    }
}
