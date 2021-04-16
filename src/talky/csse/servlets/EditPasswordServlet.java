package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/editPassword")
public class EditPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");

        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("password");
        String rePassword = request.getParameter("re_password");

        String redirect = "/editPassword?success";
        if(oldPassword != null && newPassword != null && rePassword != null){
            redirect = "/editPassword?oldPasserror";
            if(currentUser.getPassword().equals(oldPassword)){
                redirect = "/editPassword?passerror";
                if(newPassword.equals(rePassword)){
                    redirect = "/editPassword?success";
                    currentUser.setPassword(newPassword);
                }
            }
        }

        if(DBManager.updatePassword(currentUser)){
            request.getSession().setAttribute("CURRENT_USER", currentUser);
            response.sendRedirect(redirect);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}
