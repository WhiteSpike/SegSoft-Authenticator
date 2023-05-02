package accounts;

public class UserAccount extends AccountClass{

    /**
     * Constructor used for creating accounts
     * @param name Name of the new user account
     * @param password Password of the new user account
     */
    public UserAccount(String name, String password) {
        super(name, password);
    }

    /**
     * Constructor used for getting the account parameters from the database
     * @param parameters Data related to the user account
     */
    public UserAccount(String[] parameters) {
        super(parameters);
    }

    @Override
    public String GetAccountType() {
        return "user";
    }
}
