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

public class adduser extends HttpServlet {
    private static final UserService USER_SERVICE = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));

        try {
            USER_SERVICE.add(user);
        } catch (ValidationException e) {
                req.setAttribute("message", "pls check you login");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/adduser.jsp");
                requestDispatcher.forward(req, resp);
                return;
        }

        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/viewnote.jsp");
        requestDispatcher.forward(req, resp);
    }
}
