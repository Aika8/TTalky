package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.FriendRequest;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value= "/makeFriend")
public class MakeFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        String email = request.getParameter("email");

        User user = DBManager.getUser(email);

        if(user != null){
            FriendRequest request1 = new FriendRequest(null, user.getId(), currentUser.getId(), new Timestamp(System.currentTimeMillis()));
            DBManager.addRequest(request1);

            response.sendRedirect("/friends.jsp");

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/friends.jsp").forward(request, response);
    }
}
