package notes.servlets;

import notes.Services.UserService;
import notes.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class adduser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
//        PrintWriter writer = resp.getWriter();
//        writer.println("method doGet from adduser.");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        PrintWriter pw = resp.getWriter();
        pw.println("name-" + name + ", login- " + login + ", password- " + password);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/viewnote.jsp");
        requestDispatcher.forward(req, resp);
//
//        UserService userService = new UserService();
//        User user = new User();
//        user.setName(name);
//        user.setLogin(login);
//        user.setPassword(password);
//        userService.add(user);
    }
}
