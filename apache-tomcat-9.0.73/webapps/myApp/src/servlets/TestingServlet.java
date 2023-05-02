package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;
import jwt.JwtUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class TestingServlet extends HttpServlet {

    static int counter = 0;
    private Authenticator auth;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
    }

    /**
     * Prints the counter application
     * TODO: Only allow the increment operation if the person requesting the operation is authenticated (basically a JWT that expires upon one use and refreshes)
     * @param request HTTP request of the user
     * @param response HTTP response to the user
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {


        try {
            Account account = auth.CheckAuthenticatedRequest(request, response);
        } catch (Exception e) {
            out.println(e.getMessage());
        }

        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");


        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<H1>The Counter App!</H1>");
        out.println("<H1>Value=" + counter + "</H1>");
        out.print("<form action=\"");
        out.print("Test\" ");
        out.println("method=GET>");
        out.println("<br>");
        out.println("<input type=submit name=increment>");
        out.println("<br>");
        out.println("<a href=\"../manageusers\">Back</a>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
        counter++;
    }
}

