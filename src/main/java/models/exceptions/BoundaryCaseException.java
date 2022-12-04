package models.exceptions;

/**
 * @author Nithin
 * Custom Boundary Case Exception
 */
public class BoundaryCaseException extends Exception {
    /**
     * Boundary case exception Constructor
     * @param msg message to print
     */
    public BoundaryCaseException(String msg) {
        super(msg);
    }
}
