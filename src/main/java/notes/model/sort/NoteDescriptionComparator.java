package notes.model.sort;

import notes.model.Note;

import java.util.Comparator;

public class NoteDescriptionComparator implements Comparator<Note> {

    @Override
    public int compare(Note o1, Note o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
