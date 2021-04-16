package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/editProfile")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");


        String full_name = request.getParameter("full_name");
        String birthday = request.getParameter("birthday");
        String picture_url = request.getParameter("picture_url");


        if(full_name != null && birthday != null){

            currentUser.setFull_name(full_name);
            currentUser.setBirthday(birthday);
        }

        if(picture_url!= null){
            currentUser.setPictureUrl(picture_url);
        }


        if(DBManager.saveUser(currentUser)){
            request.getSession().setAttribute("CURRENT_USER", currentUser);
            response.sendRedirect("/editProfile?success");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}
