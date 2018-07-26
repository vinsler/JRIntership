package notes.servlets;

import notes.Services.UserService;
import notes.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class adduser extends HttpServlet {
    private static final UserService USER_SERVICE = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.println(new File("").getAbsolutePath());

        System.out.println("asdfasdf");

        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));

        USER_SERVICE.add(user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/viewnote.jsp");
        requestDispatcher.forward(req, resp);
    }
}
