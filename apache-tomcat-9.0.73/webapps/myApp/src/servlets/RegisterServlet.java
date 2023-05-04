package servlets;

import accounts.Account;
import accounts.RootAccount;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet used for the root to register their accounts into the application. They will need to insert a username,
 * their password and confirmation of the intended password. After sending the request, a message will pop up saying the
 * status of the operation executed to make a new account.
 *
 * As soon as the servlet is initialized, we create our authenticator object with its context so that the authenticator can know
 * where to store the accounts (Right now it's expecting to be at "WEB-INF/data/accounts.txt", if we ever decide to change where
 * to store our accounts, we must change this)
 *
 * Below the form, there's a link to the login page in case the user wishes to immediately login after creating the account
 */
public class RegisterServlet extends HttpServlet {

    private Authenticator auth;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
    }
    /**
     * Prints the form for input of account name, password and confirmation of password
     * @param request HTTP request sent by the user
     * @param response HTTP response sent to the user
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
        out.println("<form name=\"registerForm\"");
        out.println("action=\"register\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<label for=\"Password\">Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"password\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<label for=\"Confirm Password\">Confirm Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"confirmPassword\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Register\">");
        out.println("<br>");
        out.println("<br>");
        out.println("<a href=\"../UserManagement\">Back</a>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }

    /**
     * Sends a request to the authenticator module to create an account with current http session information
     * The operation will only succeed if the current user in the session is root, there is no other accounts with the same name
     * and the passwords match
     * @param request HTTP request sent by the user, where it's stored the username, password and confirmation of password
     * @param response HTTP response sent to the user, where the form will be printed along with the message of the status of the operation
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");

        out.println("<form name=\"registerForm\"");
        out.println("action=\"register\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<label for=\"Password\">Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"password\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<label for=\"Confirm Password\">Confirm Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"confirmPassword\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Register\">");
        out.println("<br>");
        try
        {
            Account account = auth.CheckAuthenticatedRequest(request, response);
            if(!(account instanceof RootAccount))
                out.println("<p>Current user does not have permission to execute this operation</p>");
            else
            {
                auth.CreateAccount(username, password, confirmPassword, "user");
                out.println("Account successfully created!");
            }
        } catch (Exception e)
        {
            out.println("<p>There is no user currently logged in</p>");
        }
        out.println("<br>");
        out.println("<br>");
        out.println("<a href=\"../UserManagement\">Back</a>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
