package authenticator;

import accounts.Account;
import exceptions.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Authenticator {

    /**
     * Creates an account used for authentication
     * @param name Name of the new account
     * @param pwd1 Password of the new account
     * @param pwd2 Confirmation of the password of the new account
     * @throws Forbidden If pwd1 does not equal to pwd2
     * @throws AccountAlreadyExists If there is already an account with given name
     */
    void CreateAccount(String name, String pwd1, String pwd2, String type) throws Forbidden, AccountAlreadyExists;

    /**
     * Deletes an account with given name
     * @param name Name of the account to be deleted
     * @throws UndefinedAccount if there isn't an account with given name
     * @throws NotLockedAccount if the account is not in the locked state
     * @throws AccountLoggedIn if the account's currently authenticated
     */
    void DeleteAccount(String name) throws UndefinedAccount, NotLockedAccount, AccountLoggedIn;

    /**
     * Gets an account with given name
     * @param name Name of the account with given name
     * @return Account associated with given name
     */
    Account GetAccount(String name);

    /**
     * Changes the password of the account with given name
     * @param name Name of the account we want to change passwords of
     * @param pwd1 New password of the account
     * @param pwd2 Confirmation of the new password of the account
     * @throws UndefinedAccount if there isn't an account with given name
     * @throws Forbidden if pwd1 does not match to pwd2
     */
    void ChangePassword(String name, String pwd1, String pwd2) throws UndefinedAccount, Forbidden;

    /**
     * Authenticates the user with given name
     * @param name Name of the account to authenticate
     * @param pwd Password of the account to authenticate
     * @return the authenticated account
     * @throws UndefinedAccount if there isn't an account with given name
     * @throws LockedAccount if the account we want to authenticate cannot be authenticated (is in locked state)
     * @throws AuthenticationError if the given password does not match to the password saved in the account we want to authenticate
     */
    Account AuthenticateUser(String name, String pwd) throws UndefinedAccount, LockedAccount, AuthenticationError;

    /**
     * Removes the authentication of the current account
     * @param userAccount Account that is currently authenticated
     * @throws AccountNotLoggedIn if the account is already not authenticated
     */
    void Logout(Account userAccount) throws AccountNotLoggedIn, UndefinedAccount;

    /**
     * Checks if the session of the HTTP request is currently authenticated
     * @param request HTTP request sent to the authenticator
     * @param response HTTP response to send to the client
     * @throws AuthenticationException if the user is not currently authenticated
     */
    void CheckAuthenticatedRequest(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException;
}
