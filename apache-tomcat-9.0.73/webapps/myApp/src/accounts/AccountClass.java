package accounts;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public abstract class AccountClass implements Account, AccountWrite{

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'F', 'C', 'T', '/', 'U', 'N', 'L', 'r',
            'o', 'c', 'k','s', '!', '!', 'd', 'i' };
    private Key key = new SecretKeySpec(keyValue, ALGO);

    private String name;
    private String hash;
    private boolean isLoggedIn;
    private boolean isLocked;

    public AccountClass(String name, String password) {
        this.name = name;
        this.hash = GetHashFromPwd(password);
        this.isLoggedIn = false;
        this.isLocked = false;
    }

    public AccountClass(String[] parameters) {
        this.name = parameters[0];
        this.hash = parameters[1];
        this.isLoggedIn = parameters[3].equals("true");
        this.isLocked = parameters[4].equals("true");
    }

    /**
     * Converts the given string which represents a password to an inconvertible hash that is then stored in the account
     * @param password String that represents the account's password
     * @return The hash of the given string
     */
    private String GetHashFromPwd(String password) {
        try
        {
            return encrypt(password);
        } catch (Exception e)
        {
            return null;
        }
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

    private String encrypt(String Data) throws Exception {
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        return java.util.Base64.
                getEncoder().encodeToString(encVal);
    }
}
