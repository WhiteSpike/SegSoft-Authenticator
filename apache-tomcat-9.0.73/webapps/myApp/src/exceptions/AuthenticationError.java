package exceptions;

public class AuthenticationError extends Exception{

    private static final String AUTHENTICATION_ERROR_MESSAGE = "<p>An error has occurred during authentication.<p>";
    public AuthenticationError(){
        super(AUTHENTICATION_ERROR_MESSAGE);
    }
}
