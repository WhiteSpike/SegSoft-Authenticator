package accounts;

public interface Account {

    String GetAccountName();

    String GetAccountHash();

    boolean isLoggedIn();

    boolean isLocked();
}
