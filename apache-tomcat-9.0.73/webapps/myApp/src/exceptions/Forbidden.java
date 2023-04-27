package exceptions;

public class Forbidden extends Exception{

    private static final String FORBIDDEN_ERROR_MESSAGE = "<p>The passwords do not match. Please try again</p>";

    public Forbidden(){
        super(FORBIDDEN_ERROR_MESSAGE);
    }
}
