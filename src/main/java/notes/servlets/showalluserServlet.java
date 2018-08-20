package notes.servlets;

import notes.Services.UserService;
import notes.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class showalluserServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listuser = userService.findAll();
        req.setAttribute("listuser", listuser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/showalluser.jsp");
        dispatcher.forward(req, resp);
    }

}
