package servlets;

import accounts.Account;
import accounts.RootAccount;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;
import exceptions.Forbidden;
import exceptions.UndefinedAccount;

import javax.naming.AuthenticationException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangePasswordServlet extends HttpServlet {

    private Authenticator auth;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<form name=\"changePasswordForm\"");
        out.println("action=\"changepassword\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<label for=\"Old Password\">Old Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"oldPassword\" required>");
        out.println("<br>");
        out.println("<label for=\"New Password\">New Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"newPassword\" required>");
        out.println("<br>");
        out.println("<label for=\"Confirm Password\">Confirm Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"confirmPassword\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Change Password\">");
        out.println("<br>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");

    }


    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<form name=\"changePasswordForm\"");
        out.println("action=\"changepassword\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<label for=\"Old Password\">Old Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"oldPassword\" required>");
        out.println("<br>");
        out.println("<label for=\"New Password\">New Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"newPassword\" required>");
        out.println("<br>");
        out.println("<label for=\"Confirm Password\">Confirm Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"confirmPassword\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Change Password\">");
        out.println("<br>");
        try
        {
            // Way to check if the user in session is the same as the one requesting to change password
            Account account = auth.CheckAuthenticatedRequest(request, response);

            if(!(account.GetAccountName().equals(username)))
                out.println("<p>Current user does not have permission to execute this operation</p>");
            else
            {
                auth.ChangePassword(username, newPassword, confirmPassword);
                out.println("Account successfully created!");
            }
        } catch (Exception e)
        {
            out.println(e.getMessage());
        }
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");

    }
}
