//package servlets;
//
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.IOException;
//
//public class SNServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Extract the requested URL to determine the action
//        String requestURL = request.getRequestURI();
//
//        // Dispatch the request to the appropriate servlet based on the requested URL
//        if (requestURL.contains("/pages")) {
//            PagesServlet pagesServlet = new PagesServlet();
//            pagesServlet.doGet(request, response);
//        } else if (requestURL.contains("/follow-requests")) {
//            FollowRequestsServlet followRequestsServlet = new FollowRequestsServlet();
//            followRequestsServlet.doGet(request, response);
//        } else if (requestURL.contains("/posts")) {
//            PostsServlet postsServlet = new PostsServlet();
//            postsServlet.doGet(request, response);
//        } else if (requestURL.contains("/delete-post")) {
//            DeletePostServlet deletePostServlet = new DeletePostServlet();
//            deletePostServlet.doGet(request, response);
//        } else if (requestURL.contains("/like-post")) {
//            LikePostServlet likePostServlet = new LikePostServlet();
//            likePostServlet.doGet(request, response);
//        } else if (requestURL.contains("/admin")) {
//            AdminServlet adminServlet = new AdminServlet();
//            adminServlet.doGet(request, response);
//        } else {
//            // Handle invalid or unrecognized requests
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Extract the requested URL to determine the action
//        String requestURL = request.getRequestURI();
//
//        // Dispatch the request to the appropriate servlet based on the requested URL
//        if (requestURL.contains("/pages")) {
//            PagesServlet pagesServlet = new PagesServlet();
//            pagesServlet.doPost(request, response);
//        } else if (requestURL.contains("/follow-requests")) {
//            FollowRequestsServlet followRequestsServlet = new FollowRequestsServlet();
//            followRequestsServlet.doPost(request, response);
//        } else if (requestURL.contains("/posts")) {
//            PostsServlet postsServlet = new PostsServlet();
//            postsServlet.doPost(request, response);
//        } else if (requestURL.contains("/delete-post")) {
//            DeletePostServlet deletePostServlet = new DeletePostServlet();
//            deletePostServlet.doPost(request, response);
//        } else if (requestURL.contains("/like-post")) {
//            LikePostServlet likePostServlet = new LikePostServlet();
//            likePostServlet.doPost(request, response);
//        } else if (requestURL.contains("/admin")) {
//            AdminServlet adminServlet = new AdminServlet();
//            adminServlet.doPost(request, response);
//        } else {
//            // Handle invalid or unrecognized requests
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Extract the requested URL to determine the action
//        String requestURL = request.getRequestURI();
//
//        // Dispatch the request to the appropriate servlet based on the requested URL
//        if (requestURL.contains("/follow-requests")) {
//            FollowRequestsServlet followRequestsServlet = new FollowRequestsServlet();
//            followRequestsServlet.doPut(request, response);
//        } else {
//            // Handle invalid or unrecognized requests
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Extract the requested URL to determine the action
//        String requestURL = request.getRequestURI();
//
//        // Dispatch the request to the appropriate servlet based on the requested URL
//        if (requestURL.contains("/delete-post")) {
//            DeletePostServlet deletePostServlet = new DeletePostServlet();
//            deletePostServlet.doDelete(request, response);
//        } else {
//            // Handle invalid or unrecognized requests
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//}
