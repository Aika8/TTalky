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

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");


        String redirect = "/login?emailerror";

        User user = DBManager.getUser(email);

        if(user != null){
            redirect = "/login?passworderror";

            if(user.getPassword().equals(password)){

                request.getSession().setAttribute("CURRENT_USER", user);
                ArrayList<User> req = DBManager.getAllFrRequests(user);

                request.getSession().setAttribute("NUMBER_OF_REQUESTS", req.size() + "");

                ArrayList<Long>notReadChats = DBManager.getnotReadMessages(user.getId());
                request.getSession().setAttribute("NOt_READ_CHATS", notReadChats.size() + "");

                redirect = "/profile?user="+user;
            }
        }

        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
