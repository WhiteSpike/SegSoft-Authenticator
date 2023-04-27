package exceptions;

public class AccountAlreadyExists extends Exception{
    private static final String ACCOUNT_ALREADY_EXISTS_ERROR_MESSAGE = "<p>There's already an account with that name.</p>";

    public AccountAlreadyExists(){
        super(ACCOUNT_ALREADY_EXISTS_ERROR_MESSAGE);
    }
}
