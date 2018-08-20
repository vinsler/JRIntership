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

public class updatenoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
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
        note.setName(req.getParameter("name"));
        note.setDescription(req.getParameter("description"));
        note.setStatus(Integer.parseInt(req.getParameter("status")));
        noteService.update(note, note.getId());
        resp.sendRedirect("/viewnote");
    }
}

