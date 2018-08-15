package notes.servlets;

import notes.Services.NoteService;
import notes.Services.UserService;
import notes.model.Note;
import notes.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class viewnoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
    private final UserService USER_SERVICE = new UserService();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        Note note = new Note();
        note.setUser(USER_SERVICE.findLogin(user));

        List<Note> notelist = noteService.findLoginNote(note);
        req.setAttribute("listnote", notelist);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/viewnote.jsp");
        dispatcher.forward(req, resp);
    }
}
