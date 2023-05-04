package accounts;

public interface Account {

    /**
     *
     * @return Name of the account
     */
    String GetAccountName();

    /**
     *
     * @return Hash of the account's password
     */
    String GetAccountHash();

    /**
     *
     * @return Type of the account
     */
    String GetAccountType();
    /**
     *
     * @return the account is currently authenticated or not
     */
    boolean isLoggedIn();

    /**
     *
     * @return the account is locked (cannot be authenticated) or not
     */
    boolean isLocked();

    /**
     * Checks if the given password is the same as the one saved in this account
     * @param pwd Password that we want to check
     * @return true if equal, false otherwise
     */
    boolean EqualHash(String pwd);

    String Encrypt(String name) throws Exception;
}
