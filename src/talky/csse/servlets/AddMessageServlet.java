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
import java.sql.Timestamp;

@WebServlet(value = "/addMessage")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        String message = request.getParameter("message");
        Long chat_id = Long.parseLong(request.getParameter("chat_id"));
        Timestamp time = new Timestamp(System.currentTimeMillis());

        Message message2;
        Chat chat = DBManager.getChat(chat_id);
        if(chat.getOpponent().getId().equals(currentUser.getId())){
            message2 = new Message(null, chat_id, chat.getUser().getId(), currentUser.getId(), message, false, time);
        }else{
            message2 = new Message(null, chat_id, chat.getOpponent().getId(), currentUser.getId(), message, false, time);
        }

        chat.setLatestMessage(message);
        chat.setLatestMessageTime(time);
        DBManager.saveChat(chat);
        DBManager.addMessage(message2);
        response.sendRedirect("/individualChat?id="+chat_id);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
