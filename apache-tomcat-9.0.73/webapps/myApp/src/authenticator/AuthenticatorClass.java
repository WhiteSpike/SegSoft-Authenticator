package authenticator;

import accounts.Account;
import accounts.AccountWrite;
import accounts.RootAccount;
import accounts.UserAccount;
import exceptions.*;

import javax.naming.AuthenticationException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Logger;

public class AuthenticatorClass implements Authenticator{

    private static final Logger logger = Logger.getLogger(Authenticator.class.getName());
    private String filePath;

    public AuthenticatorClass(ServletContext context)
    {
        this.filePath = context.getRealPath("WEB-INF/data");
    }

    @Override
    public void CreateAccount(String name, String pwd1, String pwd2, String type) throws Forbidden, AccountAlreadyExists {
        logger.info("Received 'Create Account' request for name:" + name);
        Account account = GetAccount(name);
        if (account != null) throw new AccountAlreadyExists();
        if (!pwd1.equals(pwd2)) throw new Forbidden();
        account = new UserAccount(name, pwd1);
        WriteAccountToFile(account);
    }

    private void WriteAccountToFile(Account account) {
        try {
            FileWriter out = new FileWriter(this.filePath + "/accounts.txt", true);
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
        logger.info("Received 'Delete Account' request for name: " + name);
        Account account = GetAccount(name);
        if (account == null) throw new UndefinedAccount();
        if (account.isLoggedIn()) throw new AccountLoggedIn();
        if (!account.isLocked()) throw new NotLockedAccount();
        RemoveAccountFromFile(name);
    }

    private void RemoveAccountFromFile(String name) {
        try {
            File inFile = new File(this.filePath + "/accounts.txt");
            File outFile = new File(this.filePath + "/tempaccounts.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            FileWriter out = new FileWriter(outFile, false);
            String line;
            while((line = in.readLine()) != null){
                line += '\n';
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
            FileReader file = new FileReader(this.filePath + "/accounts.txt");
            BufferedReader in = new BufferedReader(file);
            String line;
            while((line = in.readLine()) != null){
                String[] parameters = line.split(",");
                String accountName = parameters[0];

                if (!accountName.equals(name)) continue;
                return switch (parameters[2]) {
                    case "user" -> new UserAccount(parameters);
                    case "root" -> new RootAccount(parameters);
                    default -> null;
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void ChangePassword(String name, String pwd1, String pwd2) throws UndefinedAccount, Forbidden {
        logger.info("Received 'Change Password' request for name: " + name);
        Account account = GetAccount(name);

        if (account == null) throw new UndefinedAccount();
        if (!pwd1.equals(pwd2)) throw new Forbidden();

        AccountWrite accountWrite = (AccountWrite) account;
        accountWrite.SetAccountHash(pwd1);

        WriteAccountToFile(accountWrite);
    }

    @Override
    public Account AuthenticateUser(String name, String pwd) throws UndefinedAccount, LockedAccount, AuthenticationError {
        logger.info("Received 'Authenticate User' request for name: " + name);
        Account account = GetAccount(name);

        if (account == null) throw new UndefinedAccount();
        if (account.isLocked()) throw new LockedAccount();
        if (!account.EqualHash(pwd)) throw new AuthenticationError();

        AccountWrite accountWrite = (AccountWrite) account;
        accountWrite.SetIsLoggedIn(true);

        RemoveAccountFromFile(name);
        WriteAccountToFile(accountWrite);

        return accountWrite;
    }

    @Override
    public void Logout(Account account) throws UndefinedAccount, AccountNotLoggedIn {
        logger.info("Received 'Log Out' request.");
        if (account == null) throw new UndefinedAccount();
        if (!account.isLoggedIn()) throw new AccountNotLoggedIn();

        AccountWrite accountWrite = (AccountWrite) account;
        accountWrite.SetIsLoggedIn(false);

        WriteAccountToFile(accountWrite);
    }

    @Override
    public Account CheckAuthenticatedRequest(HttpServletRequest request, HttpServletResponse response) throws AuthenticationError {
        logger.info("Received 'Check Authenticated Request' request.");
        // Get the parameters from the http session
        String JWT = request.getParameter("JWT");
        // Plan is JWT contains username and expire date
        String name = null; // TODO Get name from JWT after validating it
        // (Optional) Check if token expired
        // (Optional) One time use token
        // Look for account with name
        Account account = GetAccount(name);
        if (account == null || !account.isLoggedIn()) throw new AuthenticationError();
        return account;
    }
}
