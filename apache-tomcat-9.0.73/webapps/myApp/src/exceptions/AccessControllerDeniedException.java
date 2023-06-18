package exceptions;

public class AccessControllerDeniedException extends Exception{
    private static final String DENIED_MESSAGE = "<p>Access denied! You don't have enough permissions to execute this!</p>";

    public AccessControllerDeniedException(){
        super(DENIED_MESSAGE);
    }
}
