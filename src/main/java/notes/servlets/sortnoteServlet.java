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
import javax.servlet.http.HttpSession;
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
                req.getSession().setAttribute("sortid", 1);
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "2": {
                Collections.sort(notelist, new NoteNameComparator());
                req.getSession().setAttribute("sortid", 2);
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "3": {
                Collections.sort(notelist, new NoteDescriptionComparator());
                req.getSession().setAttribute("sortid", 3);
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "4": {
                Collections.sort(notelist, new NoteCreateDateComparator());
                req.getSession().setAttribute("sortid", 4);
                reqSetRequestDispatcher(req, resp);
                break;
            }
            case "5": {
                Collections.sort(notelist, new NoteStatusComparator());
                req.getSession().setAttribute("sortid", 5);
                reqSetRequestDispatcher(req, resp);
                break;
            }
        }
    }

    private void reqSetRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("sortnote", notelist);

        Integer tmpint = (int)(long) req.getSession().getAttribute("pointer");
        if (tmpint >= notelist.size()) {
            req.getSession().setAttribute("pointer", notelist.size() - notelist.size() % 10);
        } else {
            req.getSession().setAttribute("pointer", tmpint - 10);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/viewnote");
        requestDispatcher.forward(req, resp);
    }

    public List<Note> sortNoteList(HttpServletRequest req, List<Note> srtnotelist){
        switch ((int) req.getSession().getAttribute("sortid")) {
            case 1: {
                Collections.sort(srtnotelist, new NoteIdComparator());
                break;
            }
            case 2: {
                Collections.sort(srtnotelist, new NoteNameComparator());
                break;
            }
            case 3: {
                Collections.sort(srtnotelist, new NoteDescriptionComparator());
                break;
            }
            case 4: {
                Collections.sort(srtnotelist, new NoteCreateDateComparator());
                break;
            }
            case 5: {
                Collections.sort(srtnotelist, new NoteStatusComparator());
                break;
            }
        }
        return srtnotelist;
    }
}

