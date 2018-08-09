package notes.servlets;

import notes.Services.NoteService;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Note> notelist =  noteService.findAll(); // todo add findbylogin in notes

        req.setAttribute("notelist", notelist);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/viewnote.jsp");
        dispatcher.forward(req, resp);
    }
}
