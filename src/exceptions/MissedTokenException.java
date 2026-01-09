package exceptions;

/**
 * fake class
 */
public abstract class MissedTokenException extends Exception{
    public MissedTokenException() {
    }

    public MissedTokenException(String message) {
        super(message);
    }
}
