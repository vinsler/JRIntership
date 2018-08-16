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
import java.io.PrintWriter;
import java.util.List;

public class addnoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
    private final UserService USER_SERVICE = new UserService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Note note = new Note();
        if (req.getParameter("name").equals("") ||
                req.getParameter("description").equals("")) {
            req.setAttribute("help", "Name & Description must be!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/addnote.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }
        note.setName(req.getParameter("name"));
        note.setDescription(req.getParameter("description"));
        User user = new User();
        user.setLogin(req.getSession().getAttribute("login").toString());
        note.setUser(USER_SERVICE.findLoginBySession(user));

        String checkbox = req.getParameter("status");
        if (checkbox == null) {
            note.setStatus(0);
        } else {
            note.setStatus(1);
        }
        noteService.add(note);
        List<Note> notelist = noteService.findLoginNote(note);
        req.setAttribute("listnote", notelist);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/viewnote.jsp");
        requestDispatcher.forward(req, resp);
    }
}
