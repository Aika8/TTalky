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

@WebServlet(value="/chatHandler")
public class ChatHandlerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        String redirect = request.getParameter("redirect");
        String email = request.getParameter("receiver_email");
        String message = request.getParameter("message");

        User receiver = DBManager.getUser(email);

        Timestamp time = new Timestamp(System.currentTimeMillis());

        Chat chat = DBManager.getChat(currentUser.getId(), receiver.getId());
        if(chat != null){
            Message message1 = new Message(null, chat.getId(), receiver.getId(), currentUser.getId(), message, false, time);
            chat.setLatestMessage(message);
            chat.setLatestMessageTime(time);
            DBManager.saveChat(chat);
            DBManager.addMessage(message1);
        }else{
            Chat newchat = new Chat(null, currentUser, receiver, time, message, time);
            Long chatid = DBManager.addChat(newchat);
            Message message2 = new Message(null, chatid, receiver.getId(), currentUser.getId(), message, false, time);
            DBManager.addMessage(message2);
        }

        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
