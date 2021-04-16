package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/friendProfile")
public class FreindProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
            String email = request.getParameter("email");
            User user = DBManager.getUser(email);
            if(user != null){
                ArrayList<Long>friends = DBManager.getAllFrId(currentUser);
                Boolean isFriend = friends.contains(user.getId());
                request.setAttribute("isFriend", isFriend);
                ArrayList<Long>myreq = DBManager.getAllMyRequests(currentUser);
                Boolean isReq = myreq.contains(user.getId());
                request.setAttribute("isReq", isReq);
                request.setAttribute("user", user);
            request.setAttribute("posts", DBManager.getAllPostsByUser(user.getId()));
            request.getRequestDispatcher("/friendProfile.jsp").forward(request, response);

            }else{
                response.sendRedirect("/friend?search=");
            }
    }
}
