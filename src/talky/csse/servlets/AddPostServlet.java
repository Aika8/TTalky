package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.Post;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/addPost")
public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String short_content = request.getParameter("short_content");
        String content = request.getParameter("content");
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Post post = new Post(null, currentUser, title, short_content, content, timestamp);

        DBManager.addPost(post);

        response.sendRedirect("/myPosts");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
