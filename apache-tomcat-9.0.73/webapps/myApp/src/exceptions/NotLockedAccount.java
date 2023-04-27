package exceptions;

public class NotLockedAccount extends Exception{

    private static final String NOT_LOCKED_ERROR_MESSAGE = "<p>This account is currently not locked from logging in.</p>";

    public NotLockedAccount(){
        super(NOT_LOCKED_ERROR_MESSAGE);
    }
}
