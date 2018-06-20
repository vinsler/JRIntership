package notes.Services;

import notes.dao.NoteStore;
import notes.dao.Store;
import notes.exception.ResourceException;
import notes.exception.ValidationException;
import notes.exception.SqlAccessException;
import notes.model.Note;

import java.util.ArrayList;
import java.util.List;

// todo check exist table BEFORE use NOTESTORE !!!

public class NoteService {
    private static final Store NOTESTORE = NoteStore.getInstance();

    public void add(Note note) {
        checkNote(note, 1);
        try {
            NOTESTORE.add(note);
        } catch (SqlAccessException e) {
                // вот сюда выбрасывается SqlAccessException(SQL_ERR_MSG, e);
            e.printStackTrace();
        }
    }

    public void update(Note note, Integer i) {
        checkNote(note, i);
        try {
            NOTESTORE.update(note, i);
        } catch (SqlAccessException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer i) {
        if (NOTESTORE.findOne(i) != null) {
            try {
                NOTESTORE.delete(i);
            } catch (SqlAccessException e) {
                e.printStackTrace();
            }
        } else {
            throw new ResourceException();
        }
    }

    public Note findOne(Integer i) {
        try {
            Note note = (Note) NOTESTORE.findOne(i);
            if (note != null) {
                return note;
            }
            return null;
        } catch (SqlAccessException e) {
            e.printStackTrace();
            throw new ResourceException();
        }
    }

    public List<Note> findAll() {
        List<Note> listNote = new ArrayList<>();
        try {
            listNote = NOTESTORE.findAll();
            if (!listNote.isEmpty()) {
                return listNote;
            }
            return listNote;
        } catch (SqlAccessException e) {
            e.printStackTrace();
            throw new ResourceException();
        }
    }

    private void checkNote(Note note, Integer i) {
        if (note.getName() == null) {
            throw new ValidationException("ERR_NAME");
        } else if (note.getUser() == null) {
            throw new ValidationException("ERR_USER_NOT_FOUND");
        } else if (note.getUser().getId() < 1 || i < 1) {
            throw new ValidationException("ERR_USER_ID");
        }
    }
}

