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

public class selectnoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
    private final UserService USER_SERVICE = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user = (User) req.getSession().getAttribute("user");
        Note note = new Note();
        note.setUser(USER_SERVICE.findLogin(user));
        List<Note> notelist = noteService.findLoginNote(note);

        List<Note> srtlist = (List<Note>)req.getSession().getAttribute("sortnote");
        if (srtlist == null) {
            req.setAttribute("listnote", notelist);
        } else {
            req.setAttribute("listnote", srtlist);
        }

        Integer point = (int)(long)req.getSession().getAttribute("pointer");
        switch (req.getParameter("id")) {
            case "1": {
                if (point >= notelist.size()) {
                    point = notelist.size() - notelist.size() % 10;
                }
                break;
            }
            case "2": {
                point = point - 20;
                if (point < 0) {
                    point = 0;
                }
                break;
            }
        }
        req.getSession().setAttribute("pointer", point);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/viewnote.jsp");
        dispatcher.forward(req, resp);
    }
}
