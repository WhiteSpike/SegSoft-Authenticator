package accounts;

public class RootAccount extends AccountClass{

    /**
     * Constructor used for creating accounts (if we ever decide to let the client create more than one root)
     * @param name Name of the new user account
     * @param password Password of the new user account
     */
    public RootAccount(String name, String password) {
        super(name, password);
    }

    /**
     * Constructor used for getting the account parameters from the database
     * @param parameters Data related to the user account
     */
    public RootAccount(String[] parameters) {
        super(parameters);
    }

    @Override
    public String GetAccountType() {
        return "root";
    }

}
