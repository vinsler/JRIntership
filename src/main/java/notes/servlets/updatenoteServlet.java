package notes.servlets;

import notes.Services.NoteService;
import notes.Services.UserService;
import notes.exception.ValidationException;
import notes.model.Note;
import notes.model.User;
import notes.model.sort.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class updatenoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
    private final UserService USER_SERVICE = new UserService();
    private Note note = new Note();
    private User user = new User();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        note = noteService.findOne(Integer.valueOf(req.getParameter("id")));
        user = (User) req.getSession().getAttribute("user");
        note.setUser(user);
        req.setAttribute("currentnote", note);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/updatenote.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("name").equals("") ||
                req.getParameter("description").equals("")) {
            reqSetRequestDispatcher(req, resp, 1);
        }

        try {
            Integer tint = Integer.parseInt(req.getParameter("status"));
            if (tint < 0 || tint > 1) {
                reqSetRequestDispatcher(req, resp, 2);
                return;
            }
        } catch (NumberFormatException e) {
            reqSetRequestDispatcher(req, resp, 2);
            return;
        }

        note.setName(req.getParameter("name"));
        note.setDescription(req.getParameter("description"));
        note.setStatus(Integer.valueOf(req.getParameter("status")));
        noteService.update(note, note.getId());

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

    private void reqSetRequestDispatcher(HttpServletRequest req, HttpServletResponse resp, Integer id) throws ServletException, IOException {
        if (id == 2) {
            req.setAttribute("help1", "Status must be '0'[not done] or '1'[done]!");
        } else {
            req.setAttribute("help1", "Name & Description can't be empty!");
        }
        req.setAttribute("currentnote", note);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/updatenote.jsp");
        requestDispatcher.forward(req, resp);
    }
}

