package notes.Services;

import notes.dao.NoteStore;
import notes.dao.Store;
import notes.exception.ValidationException;
import notes.model.Note;

import java.util.List;

public class NoteService {
    private static final Store NOTESTORE = NoteStore.getInstance();

    public void add(Note note) {
        checkNote(note, 1);
        NOTESTORE.add(note);
    }

    public void update(Note note, Integer i) {
        checkNote(note, i);
        NOTESTORE.update(note, i);
    }

    public void delete(Integer i) {
        if (i == null || i <= 0) {
            throw new ValidationException("ERR_ID");
        }
        NOTESTORE.delete(i);

    }

    public Note findOne(Integer i) {
        if (i == null || i <= 0) {
            throw new ValidationException("ERR_ID");
        }
        return (Note) NOTESTORE.findOne(i);

    }

    public List<Note> findAll() {

        return NOTESTORE.findAll();

    }

    private void checkNote(Note note, Integer i) {
        if (i == null || i <= 0) {
            throw new ValidationException("ERR_ID");
        } else if (note.getName() == null) {
            throw new ValidationException("ERR_NAME");
        } else if (note.getUser() == null) {
            throw new ValidationException("ERR_USER_NOT_FOUND");
        } else if (note.getUser().getId() < 1 || i < 1) {
            throw new ValidationException("ERR_USER_ID");
        }
    }
}

