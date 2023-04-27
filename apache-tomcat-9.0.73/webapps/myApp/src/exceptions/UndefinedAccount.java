package exceptions;

public class UndefinedAccount extends Exception{

    private static final String UNDEFINED_ERROR_MESSAGE = "<p>This account does not exist in our database.</p>";

    public UndefinedAccount(){
        super(UNDEFINED_ERROR_MESSAGE);
    }
}
