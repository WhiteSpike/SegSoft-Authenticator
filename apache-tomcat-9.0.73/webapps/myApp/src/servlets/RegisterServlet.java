package servlets;

import authenticator.Authenticator;
import authenticator.AuthenticatorClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    private final static Authenticator auth = new AuthenticatorClass();

    /**
     * Prints the form for input of account name, password and confirmation of password
     * @param request
     * @param response
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<form name=\"loginform\"");
        out.println("action=\"localhost:8080/myApp/manageusers/login\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<label for=\"Password\">Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"password\" required>");
        out.println("<br>");
        out.println("<label for=\"Confirm Password\">Confirm Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"confirmPassword\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Register\">");
        out.println("<br>");
        out.println("<a href=\"login\">Log in</a>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
