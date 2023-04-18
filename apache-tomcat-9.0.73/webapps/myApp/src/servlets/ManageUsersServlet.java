package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ManageUsersServlet extends HttpServlet {

    static Account authUser = null;
    static Authenticator authenticator = new AuthenticatorClass();
    private static final String USER = "username";
    private static final String PWD = "password";

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
                "<li><a href=\"CreateAccount\">Create Account</a></li>\n" +
                "<li><a href=\"DeleteAccount\">Delete Account</a></li>\n" +
                "<li><a href=\"ChangePassword\">Change Password</a></li>\n" +
                "    <li><a href=\"Logout\">Logout</a></li>\n" +
                "</ul>\n" +
                "</body></html>");
    }
}
