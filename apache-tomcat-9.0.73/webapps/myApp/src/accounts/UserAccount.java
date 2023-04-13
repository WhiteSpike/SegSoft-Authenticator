package accounts;

public class UserAccount implements Account, AccountWrite{

    private String name;
    private String hash;
    private boolean isLoggedIn;
    private boolean isLocked;

    public UserAccount(String name, String password) {
        this.name = name;
        this.hash = GetHashFromPwd(password);
        this.isLoggedIn = false;
        this.isLocked = false;
    }

    private String GetHashFromPwd(String password) {
        return null;
    }

    @Override
    public String GetAccountName() {
        return this.name;
    }

    @Override
    public String GetAccountHash() {
        return this.hash;
    }

    @Override
    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public void SetIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public void SetIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public void SetAccountName(String name) {
        this.name = name;
    }

    @Override
    public void SetAccountHash(String pwd) {
        this.hash = GetHashFromPwd(pwd);
    }

    @Override
    public boolean EqualHash(String pwd) {
        String hash = GetHashFromPwd(pwd);
        return this.hash.equals(hash);
    }
}
