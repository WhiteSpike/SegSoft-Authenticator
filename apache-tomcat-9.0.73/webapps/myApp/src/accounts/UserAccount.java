package accounts;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64.*;
public class UserAccount implements Account, AccountWrite{

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'F', 'C', 'T', '/', 'U', 'N', 'L', 'r',
            'o', 'c', 'k','s', '!', '!', 'd', 'i' };
    private Key key = new SecretKeySpec(keyValue, ALGO);

    private String name;
    private String hash;
    private String type;
    private boolean isLoggedIn;
    private boolean isLocked;

    public UserAccount(String name, String password) {
        this.name = name;
        this.hash = GetHashFromPwd(password);
        this.type = "user";
        this.isLoggedIn = false;
        this.isLocked = false;
    }

    public UserAccount(String[] parameters) {
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

    private String encrypt(String Data) throws Exception {
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        return java.util.Base64.
                getEncoder().encodeToString(encVal);
    }
}
