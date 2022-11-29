package exceptions;

/**
 * @author Nithin
 * Custom Invalid Move Exception
 */
public class InvalidMoveException extends Exception{
    /**
     * @param msg
     */
    public InvalidMoveException(String msg) {
        super(msg);
    }
}
