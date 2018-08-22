package notes.model.sort;

import notes.model.Note;

import java.util.Comparator;

public class NoteStatusComparator implements Comparator<Note> {

    @Override
    public int compare(Note o1, Note o2) {
        Integer int1 = new Integer(o1.getStatus());
        Integer int2 = new Integer(o2.getStatus());
        return int1.compareTo(int2);
    }
}
