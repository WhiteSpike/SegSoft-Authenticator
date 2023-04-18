package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        /*
        try {
            String name = request.getParameter("username");
            String pwd = request.getParameter("password");
            authUser = authenticator.AuthenticateUser(name, pwd);
            HttpSession session = request.getSession(true);
            session.setAttribute(USER, authUser.GetAccountName());
            session.setAttribute(PWD, authUser.GetAccountHash());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        */
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
