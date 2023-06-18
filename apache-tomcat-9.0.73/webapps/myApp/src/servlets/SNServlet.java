package servlets;

import accounts.Account;
import authenticator.Authenticator;
import authenticator.AuthenticatorClass;
import exceptions.AccessControllerDeniedException;
import exceptions.AccessControllerOwnerException;
import exceptions.AuthenticationError;
import io.jsonwebtoken.Claims;
import jwt.JwtUtil;
import socialNetwork.FState;
import socialNetwork.PageObject;
import socialNetwork.PostObject;
import socialNetwork.SN;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static jwt.JwtUtil.parseJWT;

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
        out.println("<form name=\"inputform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<label for=\"page_id\">Page ID:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"page_id\">");
        out.println("<br>");
        out.println("<label for=\"user_id\">User ID:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"user_id\">");
        out.println("<br>");
        out.println("<label for=\"post_id\">Post ID:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"post_id\">");
        out.println("<br>");
        out.println("<label for=\"email\">Email:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"email\">");
        out.println("<br>");
        out.println("<label for=\"page_title\">Page Title:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"page_title\">");
        out.println("<br>");
        out.println("<label for=\"page_pic\">Page Picture:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"page_pic\">");
        out.println("<br>");
        out.println("<label for=\"post_text\">Post Text:</label>");
        out.println("<br>");
        out.println("<input type=\"text\" name=\"post_text\">");
        out.println("<br>");
        out.println("</form>");

        // Create Page form
        out.println("<form name=\"newpageform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"newpage\" value=\"Create Page\">");
        out.println("</form>");

        // Get Page form
        out.println("<form name=\"getpageform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"getpage\" value=\"Get Page\">");
        out.println("</form>");

        // Delete Page form
        out.println("<form name=\"deletepageform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"deletepage\" value=\"Delete Page\">");
        out.println("</form>");

        // Follow Request form
        out.println("<form name=\"followrequestform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"followrequest\" value=\"Follow Request\">");
        out.println("</form>");

        // New Post form
        out.println("<form name=\"newpostform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"newpost\" value=\"New Post\">");
        out.println("</form>");

        // Get Post form
        out.println("<form name=\"getpostform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"getpost\" value=\"Get Post\">");
        out.println("</form>");

        // Delete Post form
        out.println("<form name=\"deletepostform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"deletepost\" value=\"Delete Post\">");
        out.println("</form>");

        // Like form
        out.println("<form name=\"likeform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"like\" value=\"Like\">");
        out.println("</form>");

        // Get Pages form
        out.println("<form name=\"getpagesform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"getpages\" value=\"Get Pages\">");
        out.println("</form>");

        // Get Posts form
        out.println("<form name=\"getpostsform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"getposts\" value=\"Get Posts\">");
        out.println("</form>");

        // Get Followers form
        out.println("<form name=\"getfollowersform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"getfollowers\" value=\"Get Followers\">");
        out.println("</form>");

        // Get Likes form
        out.println("<form name=\"getlikesform\"");
        out.println("action=\"SNServlet\" method=\"POST\">");
        out.println("<input type=\"submit\" name=\"getlikes\" value=\"Get Likes\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageId = request.getParameter("page_id");
        String userId = request.getParameter("user_id");
        String postId = request.getParameter("post_id");
        String email = request.getParameter("email");
        String pageTitle = request.getParameter("page_title");
        String pagePic = request.getParameter("page_pic");
        String postText = request.getParameter("post_text");
        String username = request.getParameter("user_id"); // this should get user from the token
        PrintWriter out = response.getWriter();
        String jwt = (String) request.getSession().getAttribute("jwt");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");

        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");

        if (request.getParameter("newpage") != null) {
            try {
                handleNewPage(userId, email, pageTitle, pagePic, jwt);
                out.println("<p>Page successfully created!</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while creating the page.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("getpage") != null) {
            try {
                PageObject page = handleGetPage(Integer.parseInt(pageId), jwt);
                out.println("<p>Page successfully retrieved!</p>");
                out.println(page);
            } catch (Exception e) {
                out.println("<p>An error occurred while retrieving the page.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("deletepage") != null) {
            try {
                handleDeletePage(Integer.parseInt(pageId), jwt);
                out.println("<p>You have deleted page" + pageId + ".</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while deleting a page.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("followrequest") != null) {
            try {
                handleFollowRequest(pageId, request, response, jwt);
                out.println("<p>You have requested to follow " + pageId + "page.</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while requesting a possibility to follow.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("newpost") != null) {
            try {
                LocalDateTime date = LocalDateTime.now();
                handleNewPost(Integer.parseInt(pageId), date.toString(), postText, jwt);
                out.println("<p>You have made a post.</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while posting.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("getpost") != null) {
            try {
                PostObject post = handleGetPost(Integer.parseInt(postId), jwt);
                out.println("<p>You retrieved a post.</p>");
                out.println(post);
            } catch (Exception e) {
                out.println("<p>An error occurred while retrieving post.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("deletepost") != null) {
            try {
                handleDeletePost(Integer.parseInt(postId), jwt);
                out.println("<p>You deleted post.</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while deleting post.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("like") != null) {
            try {
                handleLike(Integer.parseInt(pageId), Integer.parseInt(postId), Integer.parseInt(userId), jwt);
                out.println("<p>You are now liking post"+ postId+".</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while .</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("getpages") != null) {
            try {
                out.println(handleGetPages(jwt));
            } catch (Exception e) {
                out.println("<p>An error occurred while getting pages.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("getposts") != null) {
            try {
                out.println(handleGetPosts(Integer.parseInt(pageId), jwt));
            } catch (Exception e) {
                out.println("<p>An error occurred while getting posts.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("getfollowers") != null) {
            try {
                out.println(handleGetFollowers(Integer.parseInt(pageId), jwt));
                out.println("<p>You are now  " + username + ".</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while getting followers.</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        else if (request.getParameter("getlikes") != null) {
            try {
                out.println(handleGetLikes(Integer.parseInt(postId), jwt));
                out.println("<p>You are now viewing who liked post no." + postId + ".</p>");
            } catch (Exception e) {
                out.println("<p>An error occurred while getting likes .</p>");
                out.println("<p>Error message: " + e.getMessage() + "</p>");
            }
        }
        out.println("<br>");
        out.println("<a href=\"../SocialNetwork/SNServlet\">Back</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void handleNewPage(String username, String email, String pageTitle, String pagePic, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "newpage");
        try {
            sn.newPage(username, email, pageTitle, pagePic);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private PageObject handleGetPage(int page_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "getpage");
        try {
            return sn.getPage(page_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDeletePage(int page_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "deletepage");
        try {
            sn.deletePage(page_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleFollowRequest(String username, HttpServletRequest request, HttpServletResponse response, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "followrequest");
        try {
            sn.follows(Integer.parseInt(auth.CheckAuthenticatedRequest(request, response).GetAccountName()), Integer.parseInt(username), FState.PENDING);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationError e) {
            throw new RuntimeException(e);
        }
    }
    private void handleNewPost(int page_id, String post_date, String post_text, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "newpost");
        try {
            sn.newPost(page_id, post_date, post_text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PostObject handleGetPost(int post_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "getpost");
        try {
            return sn.getPost(post_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDeletePost(int post_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "deletepost");
        try {
            sn.deletePost(post_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleLike(int liked_page_id, int post_id, int liker_page_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "like");
        try { //check if liker follows the page
             sn.like(liked_page_id, liker_page_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private List<PageObject> handleGetPages(String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "getpages");
        try {
            return sn.getAllPages();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private List<PostObject> handleGetPosts(int page_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "getposts");
        try {
            return sn.getPagePosts(page_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private List<PageObject> handleGetFollowers(int page_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException {
        hasPermission(jwt, "getfollowers");
        try {
            return sn.getfollowed(page_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PageObject> handleGetLikes(int post_id, String jwt) throws AccessControllerOwnerException, AccessControllerDeniedException { // request and response are for token checking
        hasPermission(jwt, "getlikes");
        try {
            return sn.getLikes(post_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasPermission(String jwt, String permission) throws AccessControllerDeniedException, AccessControllerOwnerException {
        Claims claims = parseJWT(jwt);
        if (claims != null) {
            List<String> permissions = claims.get("permissions", List.class);
            if (permissions != null) {
                return permissions.contains(permission);
            }
        }
        AccessControllerDeniedException e = new AccessControllerDeniedException();
        throw e;
    }

}
