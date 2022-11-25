package exceptions;

public class PlayerNotFoundException extends Exception{
    public PlayerNotFoundException(String msg) {
        super(msg);
    }
}
