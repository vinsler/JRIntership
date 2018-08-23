package notes.servlets;

import notes.Services.NoteService;
import notes.Services.UserService;
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

public class sortnoteServlet extends HttpServlet {
    private final UserService USER_SERVICE = new UserService();
    private NoteService noteService = new NoteService();
    private List<Note> notelist;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user = (User) req.getSession().getAttribute("user");
        Note note = new Note();
        note.setUser(USER_SERVICE.findLogin(user));
        notelist = noteService.findLoginNote(note);

        switch (req.getParameter("id")) {
            case "1": {
                Collections.sort(notelist, new NoteIdComparator());
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "2": {
                Collections.sort(notelist, new NoteNameComparator());
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "3": {
                Collections.sort(notelist, new NoteDescriptionComparator());
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "4": {
                Collections.sort(notelist, new NoteCreateDateComparator());
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "5": {
                Collections.sort(notelist, new NoteStatusComparator());
                reqSetRequestDispatcher(req, resp);
                break;
            }
        }
    }

    private void reqSetRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sortnote", notelist);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/viewnote.jsp");
        requestDispatcher.forward(req, resp);
    }
}
