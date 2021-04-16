package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/myPosts")
public class MyPostsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){
            request.setAttribute("posts", DBManager.getAllPostsByUser(currentUser.getId()));
            request.getRequestDispatcher("/myPosts.jsp").forward(request, response);

        }else{
            response.sendRedirect("/login");
        }
    }
}
