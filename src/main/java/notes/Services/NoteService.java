package notes.Services;

import notes.dao.NoteStore;
import notes.dao.UsersStore;
import notes.exception.ServiceException;
import notes.model.Note;

import java.util.ArrayList;

public class NoteService {
    private static final NoteStore NOTESTORE = NoteStore.getInstance();
    private static final UsersStore USERSTORE = UsersStore.getInstance();
    private static final String SERVICE_ERR_USER = "ERROR!!! CHECK USER.ID!";
    private static final String SERVICE_ERR_NAME = "ERROR!!! CHECK USER.NAME!";
    private static final String SERVICE_ERR_UPDATE = "ERROR!!! CHECK INPUT FOR UPDATE!";
    private static final String SERVICE_ERR_DELETE = "ERROR!!! CHECK ID FOR DELETE!";


    public void add(Note note) {
        if (note.getName() == null) {
            throw new ServiceException(SERVICE_ERR_NAME);
        } else if (note.getUser().getId() < 1) {
            throw  new ServiceException(SERVICE_ERR_USER);
        } else {
            try {
                NOTESTORE.add(note);
            } catch (RuntimeException e) {
                throw new ServiceException(SERVICE_ERR_USER, e);
            }
        }
    }

    public void update(Note note, Integer i) {
        if (note.getName() == null && note.getUser().getId() == 0) {
            throw new ServiceException(SERVICE_ERR_UPDATE);
        } else if (i < 1) {
            throw new ServiceException(SERVICE_ERR_USER);
        } else {
            try {
                NOTESTORE.update(note, i);
            } catch (RuntimeException e) {
                throw new ServiceException(SERVICE_ERR_USER, e);
            }
        }
    }

    public void delete(Integer i) {
        try {
            NOTESTORE.delete(i);
        } catch (RuntimeException e) {
            throw new ServiceException(SERVICE_ERR_DELETE, e);
        }
    }

    public Note findOne(Integer i) {
        // todo check exist table
        Note note = NOTESTORE.findOne(i);
        if (note != null) {
            System.out.println(note.getId() + " - " + note.getName() + " - " + note.getDescription());
            return note;
        }

        System.out.println("Sry, you note does't find.");
        return null;
    }

    public ArrayList<Note> findAll() {
        ArrayList<Note> listNote = new ArrayList<>();
        // todo check exist table
        listNote = NOTESTORE.findAll();

        if (listNote.isEmpty()) {
            System.out.println("Database is empty.");
        }

        return listNote;
    }
}

