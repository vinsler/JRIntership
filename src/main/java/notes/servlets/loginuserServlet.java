package notes.servlets;

import notes.Services.UserService;
import notes.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginuser extends HttpServlet {
    private final UserService USER_SERVICE = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));



        //USER_SERVICE.findOne(req.getParameter(""))

    }

}
