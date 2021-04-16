package talky.csse.servlets;


import talky.csse.db.DBManager;
import talky.csse.db.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = 0L;
        try {
            id = Long.parseLong(request.getParameter("id"));
        }catch (Exception ignored){

        }
        String back = request.getParameter("back");

        Post p = DBManager.getPost(id);
        request.setAttribute("post", p);
        request.setAttribute("back", back);
        request.getRequestDispatcher("/details.jsp").forward(request, response);
    }
}
