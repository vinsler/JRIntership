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

public class deletenoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
    private final UserService USER_SERVICE = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        noteService.delete(Integer.valueOf(req.getParameter("id")));

        User user = new User();
        user = (User) req.getSession().getAttribute("user");
        Note note = new Note();
        note.setUser(USER_SERVICE.findLogin(user));
        List<Note> notelist = noteService.findLoginNote(note);
        List<Note> srtlist = (List<Note>)req.getSession().getAttribute("sortnote");
        if (srtlist == null) {
            req.setAttribute("listnote", notelist);
        } else {
            notelist = new sortnoteServlet().sortNoteList(req, notelist);
            req.setAttribute("listnote", notelist);
        }
        Integer tmpint = (int)(long) req.getSession().getAttribute("pointer");
        if (tmpint >= notelist.size()) {
            req.getSession().setAttribute("pointer", notelist.size() - notelist.size() % 10);
        } else {
            req.getSession().setAttribute("pointer", tmpint - 10);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/viewnote.jsp");
        requestDispatcher.forward(req, resp);
    }

}
