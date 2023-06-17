package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;
import exceptions.AuthenticationError;
import jwt.JwtUtil;
import socialNetwork.FState;
import socialNetwork.SN;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class SNServlet extends HttpServlet {

    private Authenticator auth;
    private SN sn;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
    }

    public void init(SN socialNetwork) throws ServletException {
        ServletContext context = getServletContext();
        this.auth = new AuthenticatorClass(context);
        this.sn = socialNetwork;
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Welcome to the Social Network</h1>");
        out.println("<form name=\"postform\"");
        out.println("action=\"post\" method=\"POST\">");
        out.println("<label for=\"message\">Make a post:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"message\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Post\">");
        out.println("</form>");
        out.println("<br>");
        out.println("<form name=\"followform\"");
        out.println("action=\"follow\" method=\"POST\">");
        out.println("<label for=\"username\">Follow this user:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"username\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"Follow\">");
        out.println("</form>");
        out.println("<br>");
        out.println("<form name=\"viewpostsform\"");
        out.println("action=\"viewposts\" method=\"POST\">");
        out.println("<label for=\"username\">View this user posts:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"username\" required>");
        out.println("<br>");
        out.println("<input type=\"submit\" value=\"ViewUser\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        String username = request.getParameter("username");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");

        try {
            // Handle post request
            handlePost(message);
            out.println("<p>Post successfully created!</p>");
        } catch (Exception e) {
            out.println("<p>An error occurred while creating the post.</p>");
            out.println("<p>Error message: " + e.getMessage() + "</p>");
        }

        try {
            // Handle follow request
            handleFollowRequest(username, request, response);
            out.println("<p>You are now following " + username + ".</p>");
        } catch (Exception e) {
            out.println("<p>An error occurred while following the user.</p>");
            out.println("<p>Error message: " + e.getMessage() + "</p>");
        }

        try {
            // Handle view posts
            handleViewPostsRequest(username);
            out.println("<p>You are now viewing " + username + "posts.</p>");
        } catch (Exception e) {
            out.println("<p>An error occurred while viewing the posts of "+ username +".</p>");
            out.println("<p>Error message: " + e.getMessage() + "</p>");
        }

        out.println("<br>");
        out.println("<a href=\"../UserManagement\">Back</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void handlePost(String message) {
        // Logic to handle post creation
    }

    private void handleFollowRequest(String username, HttpServletRequest request, HttpServletResponse response) {
        try {
            sn.follows(Integer.parseInt(auth.CheckAuthenticatedRequest(request, response).GetAccountName()), Integer.parseInt(username), FState.PENDING);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationError e) {
            throw new RuntimeException(e);
        }
        // Logic to handle follow request
    }
    private void handleViewPostsRequest(String username) {
        // Logic to handle follow request
    }
}
