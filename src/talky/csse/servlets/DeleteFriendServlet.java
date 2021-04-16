package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteFriend")
public class DeleteFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        String email = request.getParameter("email");
        User user = DBManager.getUser(email);
        DBManager.deleteFriend(user.getId(), currentUser.getId());
        DBManager.deleteFriend(currentUser.getId(), user.getId());
        response.sendRedirect("/friendProfile?email="+email);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
