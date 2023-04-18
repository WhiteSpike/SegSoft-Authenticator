package authenticator;

import accounts.Account;
import accounts.AccountWrite;
import accounts.UserAccount;
import exceptions.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticatorClass implements Authenticator{

    public AuthenticatorClass()
    {

    }

    @Override
    public void CreateAccount(String name, String pwd1, String pwd2) throws Forbidden, AccountAlreadyExists {
        if (hasAccountsWithName(name)) throw new AccountAlreadyExists();
        if (!pwd1.equals(pwd2)) throw new Forbidden();
        Account account = new UserAccount(name, pwd1);
        // Save in file/database the account info
    }

    @Override
    public void DeleteAccount(String name) throws UndefinedAccount, NotLockedAccount, AccountLoggedIn {
        Account account = null; // Find in file/database with name
        if (account == null) throw new UndefinedAccount();
        if (account.isLoggedIn()) throw new AccountLoggedIn();
        if (!account.isLocked()) throw new NotLockedAccount();
        // Delete from file/database
    }

    @Override
    public Account GetAccount(String name) {
        // Find in file/database with name and return
        return null;
    }

    @Override
    public void ChangePassword(String name, String pwd1, String pwd2) throws UndefinedAccount, Forbidden {
        Account account = null;
        // Find in file/database with name
        if (account == null) throw new UndefinedAccount();
        if (!pwd1.equals(pwd2)) throw new Forbidden();
    }

    @Override
    public Account AuthenticateUser(String name, String pwd) throws UndefinedAccount, LockedAccount, AuthenticationError {
        AccountWrite account = null;
        // Find in file/database with name
        if (account == null) throw new UndefinedAccount();
        if (account.isLocked()) throw new LockedAccount();
        if (!account.EqualHash(pwd)) throw new AuthenticationError();
        account.SetIsLoggedIn(true);
        return account;
    }

    @Override
    public void Logout(Account account) throws AccountNotLoggedIn {
        if (!account.isLoggedIn()) throw new AccountNotLoggedIn();
        AccountWrite accountWrite = (AccountWrite) account;
        accountWrite.SetIsLoggedIn(false);
    }

    @Override
    public void CheckAuthenticatedRequest(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // Get the parameters from the http session
        // Look for account with name
        // Check if passwords match
        // (Optional) Check if token expired
        // (Optional) One time use token

    }

    private boolean hasAccountsWithName(String name){
        Account account = null;
        // Find in file/database for account with name
        return account != null;
    }
}
