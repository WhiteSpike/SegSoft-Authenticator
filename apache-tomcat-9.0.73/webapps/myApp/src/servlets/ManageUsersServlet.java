package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ManageUsersServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<form name=\"loginform\"");
        out.println("action=\"http://www.mydomain.com/login\" method=\"POST\">");
        out.println();
        out.println("<input type=“text\" size=35 value=“username”>");
        out.println();
        out.println("<input type=\"password\" size=35 value=“password”>");
        out.println();
        out.println("<input type=\"hidden\" value=redirect_url>");
        out.println();
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
