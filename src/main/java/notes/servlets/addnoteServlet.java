package notes.servlets;

import notes.Services.NoteService;
import notes.model.Note;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class addnoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Note note = new Note();
        note.setName(req.getParameter("name"));
        note.setDescription(req.getParameter("description"));

        String checkbox = req.getParameter("status");

        if (checkbox.equals("0")) { // todo
            note.setStatus(0);
        } else {
            note.setStatus(1);
        }

        noteService.add(note);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/viewnote.jsp");
        requestDispatcher.forward(req, resp);
    }
}
