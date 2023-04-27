package exceptions;

public class LockedAccount extends Exception{

    private static final String LOCKED_ERROR_MESSAGE = "<p>This account is currently locked from logging in.</p>";

    public LockedAccount(){
        super(LOCKED_ERROR_MESSAGE);
    }
}
