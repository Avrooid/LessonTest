package example.note;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тесты для класса {@link NoteLogic}
 */
public class NoteLogicTest {

    private final NoteLogic noteLogic = new NoteLogic();

    /**
     * Тест метода {@link NoteLogic#handleMessage(String)}
     */
    @Test
    public void testAddCommandAndGetNotes() {
        String result = noteLogic.handleMessage("/add Test Note!");
        assertEquals("Note added!", result);
        String actualNotes = noteLogic.handleMessage("/notes");
        assertEquals("Your notes: 1.Test Note!;", actualNotes);
    }

    /**
     * Тест метода {@link NoteLogic#handleMessage(String)}
     */
    @Test
    public void testEditCommand() {
        noteLogic.handleMessage("/add Test Note!");
        String result = noteLogic.handleMessage("/edit New Test Note!");
        assertEquals("Note edited!", result);
        String actualNotes = noteLogic.handleMessage("/notes");
        assertEquals("Your notes: 1.New Test Note!;", actualNotes);
    }

    /**
     * Тест метода {@link NoteLogic#handleMessage(String)}
     */
    @Test
    public void testDelCommand() {
        noteLogic.handleMessage("/add Test Note!");
        noteLogic.handleMessage("/add Another Note!");
        String result = noteLogic.handleMessage("/del Test Note!");
        assertEquals("Note deleted!", result);
        String actualNotes = noteLogic.handleMessage("/notes");
        assertEquals("Your notes: 1.Another Note!;", actualNotes);
    }
}