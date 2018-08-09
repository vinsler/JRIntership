package notes.servlets;

import notes.Services.UserService;
import notes.exception.ValidationException;
import notes.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginuserServlet extends HttpServlet {
    private final UserService USER_SERVICE = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        try {
            USER_SERVICE.findLogin(user);
        } catch (ValidationException e) {
            req.setAttribute("message", "pls check you data");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/loginuser.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("login", user.getLogin());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/viewnote");
        requestDispatcher.forward(req, resp);
    }

}
