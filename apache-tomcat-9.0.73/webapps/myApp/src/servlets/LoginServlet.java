package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LoginServlet extends HttpServlet {

    private final static Authenticator auth = new AuthenticatorClass();
    /**
     * Prints the form for the user to input their account details to authenticate themselves
     * @param request HTTP request of the user
     * @param response HTTP response to the user
     * @throws ServletException
     * @throws IOException
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
        out.println("action=\"myApp/manageusers/login\" method=\"POST\">");
        out.println("<br>");
        out.println("<label for=\"Username\">Username:</label>");
        out.println("<br>");
        out.println("<input type=“text\" size=35 name=\"username\" required>");
        out.println("<br>");
        out.println("<label for=\"Password\">Password:</label>");
        out.println("<br>");
        out.println("<input type=\"password\" size=35 name=\"password\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Log In\">");
        out.println("<br>");
        out.println("<a href=\"register\">Register Account</a>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
            Account account = auth.AuthenticateUser(username, password);
            // TODO Generate JWT
        } catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("localhost:8080/myApp/manageusers");
    }
}
