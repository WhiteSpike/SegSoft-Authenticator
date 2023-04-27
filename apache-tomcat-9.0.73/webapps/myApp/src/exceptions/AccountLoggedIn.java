package exceptions;

public class AccountLoggedIn extends Exception{

    private static final String ACCOUNT_LOGGED_IN_ERROR_MESSAGE = "<p>This account is currently logged in. Try again later.</p>";

    public AccountLoggedIn(){
        super(ACCOUNT_LOGGED_IN_ERROR_MESSAGE);
    }
}
