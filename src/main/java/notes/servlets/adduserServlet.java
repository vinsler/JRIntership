package notes.servlets;

import notes.Services.UserService;
import notes.exception.ValidationException;
import notes.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class adduserServlet extends HttpServlet {
    private static final UserService USER_SERVICE = new UserService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));

        try {
            USER_SERVICE.add(user);
        } catch (ValidationException e) {
            req.setAttribute("message", "please check your details!");
            req.setAttribute("help1", "name, login & password must be entered!");
            req.setAttribute("help2", "login must be unique!");
            req.setAttribute("name", user.getName());
            req.setAttribute("password", user.getPassword());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/adduser.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        resp.sendRedirect("/showalluser"); // todo

    }
}
