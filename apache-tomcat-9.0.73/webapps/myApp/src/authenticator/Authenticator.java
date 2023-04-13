package authenticator;

import accounts.Account;
import exceptions.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Authenticator {

    void CreateAccount(String name, String pwd1, String pwd2) throws Forbidden, AccountAlreadyExists;

    void DeleteAccount(String name) throws UndefinedAccount, NotLockedAccount, AccountLoggedIn;

    Account GetAccount(String name);

    void ChangePassword(String name, String pwd1, String pwd2) throws UndefinedAccount, Forbidden;

    Account AuthenticateUser(String name, String pwd) throws UndefinedAccount, LockedAccount, Forbidden, AuthenticationError;

    void Logout(Account userAccount) throws AccountNotLoggedIn;

    void CheckAuthenticatedRequest(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException;
}
