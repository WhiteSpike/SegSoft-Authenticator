package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;
import exceptions.AccountNotLoggedIn;
import exceptions.AuthenticationError;
import exceptions.UndefinedAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;

public class UserManagementServlet extends HttpServlet {

    private Authenticator auth;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
    }
    /**
     * Prints the list of links specified in the pdf, specifically in the slide number 92 ("How to demo your authenticator module")
     * @param request HTTP request of the user
     * @param response HTTP response to the user
     * @throws ServletException
     * @throws IOException
     */



    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML><html lang=\"en\"><head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>User Management</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>\n" +
                "<h3>User Management Operations:</H3>\n" +
                "<p></p>\n" +
                "<ul>\n");
        if(request.getSession().getAttribute("jwt") != null){
            out.println("<li><a href=\"Counter\">The Counter App!</a></li>\n" +
                    "<li><a href=\"UserManagement/register\">Create Account</a></li>\n" +
                    "<li><a href=\"UserManagement/delete\">Delete Account</a></li>\n" +
                    "<li><a href=\"UserManagement/ChangePassword\">Change Password</a></li>\n");
        }
        else {
            out.println("<li><a href=\"UserManagement/login\">Login</a></li>\n");
        }

           if(request.getSession().getAttribute("jwt") != null) {
               out.println("<form method=\"POST\" action=\"UserManagement\">\n" +
                       "<button type=\"submit\">Logout</button>\n" +
                       "</form>\n");
           }
        out.println("</ul>\n" +
                "</body></html>");

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user's account from the session
        Account account = null;
        try {
            account = auth.CheckAuthenticatedRequest(request, response);
        } catch (AuthenticationError e) {
            throw new RuntimeException(e);
        }
        if(account != null) {
            try {
                auth.Logout(account, request);
            } catch (AccountNotLoggedIn e) {
                throw new RuntimeException(e);
            } catch (UndefinedAccount e) {
                throw new RuntimeException(e);
            }
        }
        // Redirect the user back to the login page
        response.sendRedirect("UserManagement/login");
    }
}
