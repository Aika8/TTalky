package talky.csse.servlets;

import talky.csse.db.DBManager;
import talky.csse.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");
        String birthday = request.getParameter("birthday");

        String redirect = "/register?passworderror";

        if(password.equals(rePassword)){

            redirect = "/register?emailerror";

            User user = DBManager.getUser(email);
            if(user == null){

                User newUser = new User(null, email, password, fullName, birthday, null);
                if(DBManager.addUser(newUser)){
                    redirect = "/register?success";
                }

            }
        }
        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
