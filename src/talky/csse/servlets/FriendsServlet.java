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

@WebServlet(value = "/friend")
public class FriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){

            String search = request.getParameter("search");
            ArrayList<User>users;
            request.getSession().setAttribute("search", search);
            if(!search.equals("")){
                users = DBManager.getSearchUsers(search, currentUser);
            }else{
                users = DBManager.getAllFriends(currentUser);
            }


            ArrayList<Long>friends = DBManager.getAllFrId(currentUser);
            ArrayList<User>req = DBManager.getAllFrRequests(currentUser);
            ArrayList<Long>myreq = DBManager.getAllMyRequests(currentUser);
            request.setAttribute("myreq", myreq);
            request.setAttribute("req", req);
            request.setAttribute("users", users);
            request.setAttribute("friends", friends);
            request.getRequestDispatcher("/friends.jsp").forward(request, response);

        }else{
            response.sendRedirect("/login");
        }
    }
}
