package notes.Services;

import notes.dao.NoteStore;
import notes.model.Note;
import notes.model.Users;

import java.util.ArrayList;

public class NoteService {
    private static final NoteStore notestore = NoteStore.getInstance();

    public void add(Note note) {
        Users users = new Users();
        if (note.getName() == null) {
            System.err.println("ERROR! Check Name!");
        } else if (note.getDescription() == null) {
            System.err.println("ERROR! Check Description!");
        } else if (note.getUsers_id() < 1) {
            System.out.println("ERROR! Check Users_id");
        } else {
            notestore.add(note);
        }
    }

    public void update(Note note, Integer i) {
        // check
        notestore.update(note, i);
        // check
    }

    public void delete(Integer i) {
        // check
        notestore.delete(i);
        // check
    }

    public Note findOne(Integer i) {
        Note note = new Note();
        // check
        note = notestore.findOne(i);
        // check
        // transfer to view
        return note;
    }

    public ArrayList<Note> findAll() {
        ArrayList<Note> listNote = new ArrayList<>();
        // check
        listNote = notestore.findAll();
        // check
        return listNote;
    }
}
