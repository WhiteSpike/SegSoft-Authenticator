package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class UserManagementServlet extends HttpServlet {

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
                "<ul>\n" +
                "<li><a href=\"Counter\">The Counter App!</a></li>\n" +
                "<li><a href=\"UserManagement/login\">Login</a></li>\n" +
                "<li><a href=\"UserManagement/register\">Create Account</a></li>\n" +
                "<li><a href=\"UserManagement/delete\">Delete Account</a></li>\n" +
                "<li><a href=\"UserManagement/ChangePassword\">Change Password</a></li>\n" +
                "<li><a href=\"UserManagement/logout\">Logout</a></li>\n" +
                "</ul>\n" +
                "</body></html>");
    }
}
