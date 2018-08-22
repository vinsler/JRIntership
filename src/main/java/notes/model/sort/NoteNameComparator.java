package notes.model.sort;

import notes.model.Note;

import java.util.Comparator;

public class NoteNameComparator implements Comparator<Note> {

    @Override
    public int compare(Note o1, Note o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
