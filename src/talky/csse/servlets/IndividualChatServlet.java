package talky.csse.servlets;

import talky.csse.db.Chat;
import talky.csse.db.DBManager;
import talky.csse.db.Message;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/individualChat")
public class IndividualChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){

            Long id = Long.parseLong(request.getParameter("id"));
            ArrayList<Long>notReadChats = DBManager.getnotReadMessages(currentUser.getId());
            if(notReadChats.contains(id)){
                DBManager.makeRead(id, currentUser.getId());
                notReadChats = DBManager.getnotReadMessages(currentUser.getId());
                request.getSession().setAttribute("NOt_READ_CHATS", notReadChats.size() + "");
            }

            ArrayList<Message>messages= DBManager.getAllMessages(id);
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/IndividualChat.jsp").forward(request, response);

        }else{
            response.sendRedirect("/login");
        }

    }
}
