package accounts;

public interface AccountWrite extends Account{

    void SetIsLoggedIn(boolean isLoggedIn);

    void SetIsLocked(boolean isLocked);

    void SetAccountName(String name);

    void SetAccountHash(String pwd);

    boolean EqualHash(String pwd);
}
