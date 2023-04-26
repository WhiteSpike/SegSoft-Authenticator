package authenticator;

import accounts.Account;
import accounts.AccountWrite;
import accounts.RootAccount;
import accounts.UserAccount;
import exceptions.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class AuthenticatorClass implements Authenticator{

    public AuthenticatorClass()
    {
    }

    @Override
    public void CreateAccount(String name, String pwd1, String pwd2, String type) throws Forbidden, AccountAlreadyExists {
        Account account = GetAccount(name);
        if (account == null) throw new AccountAlreadyExists();
        if (!pwd1.equals(pwd2)) throw new Forbidden();
        account = new UserAccount(name, pwd1);
        WriteAccountToFile(account);
    }

    private void WriteAccountToFile(Account account) {
        try {
            FileWriter out = new FileWriter("accounts.txt", true);
            out.write(account.GetAccountName()
                    + "," + account.GetAccountHash()
                    + "," + account.GetAccountType()
                    + "," + account.isLoggedIn()
                    + "," + account.isLocked() + "\n");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteAccount(String name) throws UndefinedAccount, NotLockedAccount, AccountLoggedIn {
        Account account = GetAccount(name);
        if (account == null) throw new UndefinedAccount();
        if (account.isLoggedIn()) throw new AccountLoggedIn();
        if (!account.isLocked()) throw new NotLockedAccount();
        RemoveAccountFromFile(name);
    }

    private void RemoveAccountFromFile(String name) {
        try {
            File inFile = new File("accounts.txt");
            File outFile = new File("tempaccounts.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            FileWriter out = new FileWriter(outFile, false);
            String line;
            while((line = in.readLine()) != null){
                String[] parameters = line.split(",");
                String accountName = parameters[0];

                if (!accountName.equals(name))
                    out.write(line);
            }
            in.close();
            out.close();
            outFile.renameTo(inFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account GetAccount(String name) {

        try {
            FileReader file = new FileReader("accounts.txt");
            BufferedReader in = new BufferedReader(file);
            String line;
            while((line = in.readLine()) != null){
                String[] parameters = line.split(",");
                String accountName = parameters[0];

                if (!accountName.equals(name)) continue;
            switch(parameters[2]){
                case "user": return new UserAccount(parameters);
                case "root": return new RootAccount(parameters);
                default: return null;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void ChangePassword(String name, String pwd1, String pwd2) throws UndefinedAccount, Forbidden {
        Account account = GetAccount(name);

        if (account == null) throw new UndefinedAccount();
        if (!pwd1.equals(pwd2)) throw new Forbidden();

        AccountWrite accountWrite = (AccountWrite) account;
        accountWrite.SetAccountHash(pwd1);

        WriteAccountToFile(accountWrite);
    }

    @Override
    public Account AuthenticateUser(String name, String pwd) throws UndefinedAccount, LockedAccount, AuthenticationError {
        Account account = GetAccount(name);

        if (account == null) throw new UndefinedAccount();
        if (account.isLocked()) throw new LockedAccount();
        if (!account.EqualHash(pwd)) throw new AuthenticationError();

        AccountWrite accountWrite = (AccountWrite) account;
        accountWrite.SetIsLoggedIn(true);

        WriteAccountToFile(accountWrite);

        return accountWrite;
    }

    @Override
    public void Logout(Account account) throws UndefinedAccount, AccountNotLoggedIn {
        if (account == null) throw new UndefinedAccount();
        if (!account.isLoggedIn()) throw new AccountNotLoggedIn();

        AccountWrite accountWrite = (AccountWrite) account;
        accountWrite.SetIsLoggedIn(false);

        WriteAccountToFile(accountWrite);
    }

    @Override
    public void CheckAuthenticatedRequest(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // Get the parameters from the http session
        String JWT = request.getParameter("JWT");
        // Plan is JWT contains username and expire date
        String name = null; // TODO Get name from JWT after validating it
        // (Optional) Check if token expired
        // (Optional) One time use token
        // Look for account with name
        Account account = GetAccount(name);
        if (!account.isLoggedIn()) throw new AuthenticationException();

    }
}
