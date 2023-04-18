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
     * @return Java Web Token of the account
     */
    String GetJWT();
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
}
