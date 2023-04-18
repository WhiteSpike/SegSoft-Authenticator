package accounts;

public interface AccountWrite extends Account {

    /**
     * Sets the logged in state of the account
     * @param isLoggedIn New state of the account relative to being logged in
     */
    void SetIsLoggedIn(boolean isLoggedIn);

    /**
     * Sets the locked state of the account
     * @param isLocked New state of the account relative to being locked
     */
    void SetIsLocked(boolean isLocked);

    /**
     * Sets the new name of the account
     * @param name New name of the account
     */
    void SetAccountName(String name);

    /**
     * Sets the new password of the account
     * @param pwd New password of the account
     */
    void SetAccountHash(String pwd);

    /**
     * Checks if the given password is the same as the one saved in this account
     * @param pwd Password that we want to check
     * @return true if equal, false otherwise
     */
    boolean EqualHash(String pwd);
}
