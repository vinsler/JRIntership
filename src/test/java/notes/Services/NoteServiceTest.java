package notes.Services;

import notes.dao.NoteStore;
import notes.exception.ValidationException;
import notes.model.Note;
import notes.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {
    private Note note = new Note();

    @Mock // заглушка, (обьект для подмены)
    private NoteStore noteStore;

    @InjectMocks // тестируемый класс, (куда подставлять заглушку)
    private NoteService noteService = new NoteService();

    @Before
    public void setup() {
        initMocks(NoteServiceTest.class); // инит всех заглушек
    }

    @Test(expected = ValidationException.class) //
    public void TestAddWithNoteNull() {
        noteService.add(null);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithNoteNameNull(){
        note.setName(null);
        noteService.add(note);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithNoteNameIsEmpty(){
        note.setName("");
        noteService.add(note);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithUserNull(){
        note.setName("some");
        noteService.add(note);
    }

    @Test(expected = ValidationException.class)
    public void TestAddWithNoteUserIdZero(){
        User user = new User();
        user.setId(0);
        note.setName("some");
        note.setUser(user);
        noteService.add(note);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithNoteNull(){
        noteService.update(null, 1);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithIdNull(){
        noteService.update(note, null);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithIdZero(){
        noteService.update(note, 0);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithNoteNameNull(){
        note.setName(null);
        noteService.update(note, 1);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithNoteUserNull(){
        note.setName("some");
        note.setUser(null);
        noteService.update(note, 1);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithNoteUserIdNull(){
        note.setName("some");
        User user = new User();
        note.setUser(user);
        noteService.update(note, 1);
    }

    @Test(expected = ValidationException.class)
    public void TestUpdateWithNoteUserIdZero(){
        note.setName("some");
        User user = new User();
        user.setId(0);
        note.setUser(user);
        noteService.update(note, 1);
    }

    @Test
    public void TestAddWithLegalNote(){
        note.setName("some");
        User user = new User();
        user.setId(1);
        note.setUser(user);

        doNothing().when(noteStore).add(isA(Note.class));
        noteService.add(note);
        verify(noteStore, times(1)).add(note);
    }

    @Test
    public void TestUpdateWithLegalNote(){
        note.setName("some");
        User user = new User();
        user.setId(1);
        note.setUser(user);

        doNothing().when(noteStore).update(isA(Note.class), isA(Integer.class));
        noteService.update(note, 1);
        verify(noteStore, times(1)).update(note, 1);
    }

    @Test(expected = ValidationException.class)
    public void TestDeleteWithIdNull(){
        noteService.delete(null);
    }

    @Test(expected = ValidationException.class)
    public void TestDeleteWithIdZero(){
        noteService.delete(0);
    }

    @Test
    public void TestDeleteWithLegalId(){
        doNothing().when(noteStore).delete(isA(Integer.class));
        noteService.delete(1);
        verify(noteStore, times(1)).delete(1);
    }

    @Test(expected = ValidationException.class)
    public void TestFindOneWithNullId(){
        noteService.findOne(null);
    }

    @Test (expected = ValidationException.class)
    public void TestFindOneWithZeroId(){
        noteService.findOne(0);
    }

    @Test
    public void TestAddWithLegalArgument(){
        doNothing().when(noteStore).add(isA(Note.class));
        User user = new User();
        user.setId(1);
        note.setUser(user);
        note.setName("some");
        noteService.add(note);
        verify(noteStore, times(1)).add(note);
    }

    @Test
    public void TestFindOneWithLegalArgument (){
        when(noteStore.findOne(1)).thenReturn(new Note());
        assertEquals(noteService.findOne(1), noteStore.findOne(1));
    }

    @Test
    public void TestFindAllWithLegalArgument (){
        List<Note> list = new ArrayList<>();
        when(noteStore.findAll()).thenReturn(list);
        assertEquals(noteService.findAll(), noteStore.findAll());
    }
}