package pt.ulusofona.aed.deisimdb;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDirector {

    @Test
    public void testgetDirectorString() {
        Director director = new Director(1335642, "Robert Gordon", 319067);
        String expected = "1335642 | Robert Gordon | 319067";
        assertEquals(expected, director.getDirectorString());
    }

    @Test
    public void testdirectorConstructor() {
        Director director = new Director(1335642, "Robert Gordon", 319067);
        assertNotNull(director);
    }

    @Test
    public void testgetDirectorId() {
        Director director = new Director(1, "sla", 1);
        assertEquals(1, director.getDirectorId());
    }

    @Test
    public void testgetDirectorName() {
        Director director = new Director(1, "sla", 1);
        assertEquals("sla", director.getDirectorName());
    }

    @Test
    public void targetgetMovieId() {
        Director director = new Director(1, "sla", 1);
        assertEquals(1, director.getMovieId());
    }
}
