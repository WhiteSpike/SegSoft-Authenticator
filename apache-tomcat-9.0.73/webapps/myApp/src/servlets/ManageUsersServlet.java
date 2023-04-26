package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ManageUsersServlet extends HttpServlet {

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
                "<title>Apache Tomcat Examples</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>\n" +
                "<h3>User Management Operations:</H3>\n" +
                "<p></p>\n" +
                "<ul>\n" +
                "    <li><a href=\"manageusers/login\">Login</a></li>\n" +
                "<li><a href=\"manageusers/register\">Create Account</a></li>\n" +
                "<li><a href=\"manageusers/delete\">Delete Account</a></li>\n" +
                "<li><a href=\"manageusers/changepassword\">Change Password</a></li>\n" +
                "    <li><a href=\"manageusers/logout\">Logout</a></li>\n" +
                "</ul>\n" +
                "</body></html>");
    }
}
