package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;
import exceptions.AuthenticationError;
import exceptions.LockedAccount;
import exceptions.UndefinedAccount;
import jwt.JwtUtil;

import io.jsonwebtoken.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * Servlet designed for the url "UserManagement/login" where the user can authenticate their account by inserting their accout name
 * and password. If an error has ocurred while trying to authenticate the account, it will be displayed below the form.
 *
 * The form will contain two parameters: a user name and a password. Password will be hidden while name is shown.
 *
 * As soon as the servlet is initialized, we create our authenticator object with its context so that the authenticator can know
 * where to store the accounts (Right now it's expecting to be at "WEB-INF/data/accounts.txt", if we ever decide to change where
 * to store our accounts, we must change this)
 *
 * Below the form, there will be a prompt for the user to register an account which will bring out the RegisterServlet
 *
 * When the user authenticate their account, the http session will have a Java Web Token associated which will expire either
 * due to inactivity or when it's used on an operation which will renew into a new one, making the old unusable.
 */
public class LoginServlet extends HttpServlet {

    private Authenticator auth;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
    }
    /**
     * Prints the form for the user to input their account details to authenticate themselves
     * @param request HTTP request of the user
     * @param response HTTP response to the user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<form name=\"loginform\"");
        out.println("action=\"login\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<label for=\"Password\">Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"password\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Log In\">");
        out.println("<br>");
        out.println("<br>");
        out.println("<a href=\"../UserManagement\">Back</a>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");

        try{
            Account account = auth.AuthenticateUser(username, password);
            System.out.println("Creating JWT for ");
            //throw new JwtException("");
            JwtUtil jwtUtil = new JwtUtil();
            String jwt = jwtUtil.createJWT(account.GetAccountName());
            out.println("<p>You have logged in into your account!</p>");
            request.getSession().setAttribute("jwt", jwt);
        } catch (Exception e)
        {
            out.println(e.getMessage());
        }
        out.println("<a href=\"../UserManagement\">Back</a>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
