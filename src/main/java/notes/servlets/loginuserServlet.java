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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        User userT = new User();

        userT = USER_SERVICE.findLogin(user);
        if (userT == null) {
            req.setAttribute("message", "pls check you login");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/loginuser.jsp");
            requestDispatcher.forward(req, resp);
            return;
        } else if (userT.getPassword().equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("login", user.getLogin());
            resp.sendRedirect("/viewnote");
            return;
        }
        req.setAttribute("message", "pls check you password");
        req.setAttribute("log", user.getLogin());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/loginuser.jsp");
        requestDispatcher.forward(req, resp);
    }

}
