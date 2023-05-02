package servlets;

import accounts.Account;
import accounts.RootAccount;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;
import exceptions.AccountLoggedIn;
import exceptions.NotLockedAccount;
import exceptions.UndefinedAccount;

import javax.naming.AuthenticationException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteServlet extends HttpServlet {

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
        out.println("<form name=\"deleteForm\"");
        out.println("action=\"delete\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Delete Account\">");
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
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<form name=\"deleteForm\"");
        out.println("action=\"delete\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Delete Account\">");
        out.println("<br>");
        out.println("<a href=\"../manageusers\">Back</a>");

        try
        {
            // Way to check if the session currently has root logged in
            Account account = auth.CheckAuthenticatedRequest(request, response);
            if(!(account instanceof RootAccount))
                out.println("<p>Current user does not have permission to execute this operation</p>");
            else
            {
                auth.DeleteAccount(username);
                out.println("Account successfully deleted!");
            }
        } catch (Exception e)
        {
            out.println(e.getMessage());
        }
        out.println("<br>");
        out.println("<a href=\"../UserManagement\">Back</a>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
