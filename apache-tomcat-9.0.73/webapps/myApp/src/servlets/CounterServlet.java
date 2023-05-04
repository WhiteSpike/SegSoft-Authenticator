package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {

	static int counter = 0;
    private Authenticator auth;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
    }

    /**
     * Prints the counter application
     * @param request HTTP request of the user
     * @param response HTTP response to the user
     * @throws ServletException
     * @throws IOException
     */
 public void doGet(HttpServletRequest request, 
  HttpServletResponse response) 
  throws ServletException, IOException
{
  PrintWriter out = response.getWriter();
  out.println("<HTML>");
  out.println("<HEAD>");
  out.println("</HEAD>");
  out.println("<BODY>");
  out.println("<H1>The Counter App!</H1>");
  out.println("<H1>Value="+counter+"</H1>");
  out.print("<form action=\"");
  out.print("Counter\" ");
  out.println("method=POST>");
  out.println("<br>");
  out.println("<input type=submit name=increment>");
  out.println("</form>");
  out.println("<li><a href=\"UserManagement\">User Management</a></li>");
  out.println("</BODY>");
  out.println("</HTML>");
 }

 public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException
 {
     PrintWriter out = response.getWriter();
     out.println("<HTML>");
     out.println("<HEAD>");
     out.println("</HEAD>");
     out.println("<BODY>");
     out.println("<H1>The Counter App!</H1>");
     try
     {
         Account account = auth.CheckAuthenticatedRequest(request, response);
         if(account == null)
             out.println("<p>Current user does not have permission to execute this operation</p>");
         else
         {
             counter++;
         }
     } catch (Exception e)
     {
         out.println("<p>There is no user currently logged in</p>");
     }
     out.println("<H1>Value="+counter+"</H1>");
     out.print("<form action=\"");
     out.print("Counter\" ");
     out.println("method=POST>");
     out.println("<br>");
     out.println("<input type=submit name=increment>");
     out.println("</form>");
     out.println("<li><a href=\"UserManagement\">User Management</a></li>");
     out.println("</BODY>");
     out.println("</HTML>");
 }
}

