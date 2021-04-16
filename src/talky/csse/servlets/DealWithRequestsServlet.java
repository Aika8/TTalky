package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.Friend;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

@WebServlet(value = "/requestDeal")
public class DealWithRequestsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        String reject = request.getParameter("reject");
        String confirm = request.getParameter("confirm");

        User user = null;

        if(reject != null){
            user = DBManager.getUser(reject);
            DBManager.deleteRequest(user.getId(), currentUser.getId());
        }
        if(confirm != null){
           user = DBManager.getUser(confirm);
            Friend he = new Friend(null, currentUser.getId(), user.getId(), new Timestamp(System.currentTimeMillis()));
            DBManager.addFriend(he);
            Friend me = new Friend(null, user.getId(), currentUser.getId(), new Timestamp(System.currentTimeMillis()));
            DBManager.addFriend(me);
            DBManager.deleteRequest(user.getId(), currentUser.getId());
        }

        ArrayList<User> req = DBManager.getAllFrRequests(user);
        request.getSession().setAttribute("NUMBER_OF_REQUESTS", req.size() + "");

        response.sendRedirect("/friend?search=");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
