package exceptions;

public class AccountNotLoggedIn extends Exception{

    private static final String ACCOUNT_NOT_LOGGED_IN_ERROR_MESSAGE = "<p>This account is no longer logged in. Try logging in and try again.</p>";

    public AccountNotLoggedIn(){
        super(ACCOUNT_NOT_LOGGED_IN_ERROR_MESSAGE);
    }
}
