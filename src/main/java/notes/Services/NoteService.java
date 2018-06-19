package notes.Services;

import notes.dao.NoteStore;
import notes.exception.ServiceResourceException;
import notes.exception.ServiceValidationException;
import notes.exception.SqlAccessException;
import notes.model.Note;

import java.util.ArrayList;

// todo check exist table BEFORE use NOTESTORE !!!

public class NoteService {
    private static final NoteStore NOTESTORE = NoteStore.getInstance();
    private static final String ERR_USER = "Service, check user id!";
    private static final String ERR_NAME = "Service, check note name!";
    private static final String ERR_UPDATE = "Service, check input for update!";
    private static final String ERR_DELETE = "Service, check id for delete!";
    private static final String ERR_NOT_FOUND = "Service, resources not found!";


    public void add(Note note) {
        if (note.getName() == null) {
            throw new ServiceValidationException(ERR_NAME);
        } else if (note.getUser().getId() < 1) {
            throw  new ServiceValidationException(ERR_USER);
        } else {
            try {
                NOTESTORE.add(note);
            } catch (RuntimeException e) {
                throw new SqlAccessException(e.toString());
            }
        }
    }

    public void update(Note note, Integer i) {
        if (note.getName() == null && note.getUser().getId() == 0) {
            throw new ServiceResourceException(ERR_UPDATE);
        } else if (i < 1) {
            throw new ServiceResourceException(ERR_USER);
        } else {
            try {
                NOTESTORE.update(note, i);
            } catch (RuntimeException e) {
                throw new SqlAccessException(e.toString());
            }
        }
    }

    public void delete(Integer i) {
        try {
            NOTESTORE.delete(i);
        } catch (RuntimeException e) {
            throw new SqlAccessException(ERR_DELETE, e);
        }
    }

    public Note findOne(Integer i) {
        Note note = NOTESTORE.findOne(i);
        if (note != null) {
            return note;
        }
        throw new ServiceResourceException(ERR_NOT_FOUND);
    }

    public ArrayList<Note> findAll() {
        ArrayList<Note> listNote = new ArrayList<>();
        listNote = NOTESTORE.findAll();
        if (!listNote.isEmpty()) {
            return listNote;
        }
        throw new ServiceResourceException(ERR_NOT_FOUND);
    }
}

