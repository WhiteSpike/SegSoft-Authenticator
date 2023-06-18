package exceptions;

public class AccessControllerOwnerException extends Exception{
    private static final String OWNER_MESSAGE = "<p>Access denied! You're not the owner of this page!</p>";

    public AccessControllerOwnerException(){
        super(OWNER_MESSAGE);
    }
}
