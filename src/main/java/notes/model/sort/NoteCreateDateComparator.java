package notes.model.sort;

import notes.model.Note;

import java.util.Comparator;

public class NoteCreateDateComparator implements Comparator<Note> {

    @Override
    public int compare(Note o1, Note o2) {
        return o1.getCreateDate().compareTo(o2.getCreateDate());
    }
}
