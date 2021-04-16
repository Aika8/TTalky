package talky.csse.servlets;

import talky.csse.db.Chat;
import talky.csse.db.DBManager;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/chat")
public class ChatsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){

            String searchChat = request.getParameter("searchChat");
            ArrayList<Chat>chats;
            request.getSession().setAttribute("searchChat", searchChat);
            if(!searchChat.equals("")){
                chats = DBManager.getSearchChats(searchChat, currentUser);
            }else{
                chats = DBManager.getAllChats(currentUser.getId());
            }

            ArrayList<Long>notReadChats = DBManager.getnotReadMessages(currentUser.getId());
            request.setAttribute("notReadChats", notReadChats);
            request.setAttribute("chats", chats);
            request.getRequestDispatcher("/chats.jsp").forward(request, response);

        }else{
            response.sendRedirect("/login");
        }
    }
}
