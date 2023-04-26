package accounts;

public class RootAccount implements Account, AccountWrite{
    private String name;
    private String hash;
    private String type;
    private boolean isLoggedIn;
    private boolean isLocked;

    public RootAccount(String name, String password) {
        this.name = name;
        this.hash = GetHashFromPwd(password);
        this.type = "user";
        this.isLoggedIn = false;
        this.isLocked = false;
    }

    public RootAccount(String[] parameters) {
        this.name = parameters[0];
        this.hash = parameters[1];
        this.type = parameters[2];
        this.isLoggedIn = parameters[3].equals("true");
        this.isLocked = parameters[4].equals("true");
    }

    /**
     * Converts the given string which represents a password to a unconvertible hash that is then stored in the account
     * @param password String that represents the account's password
     * @return The hash of the given string
     */
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
    public String GetAccountType() {
        return this.type;
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
