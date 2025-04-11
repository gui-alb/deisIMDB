package pt.ulusofona.aed.deisimdb;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestActor {

    @Test
    public void testGetMovieId() {
        Actor actor = new Actor(1, "Actor 1", "M", 1001);
        assertEquals(1001, actor.getMovieId());
    }

    @Test
    public void testGetActorStringMasculino() {
        Actor actor = new Actor(1, "Actor 1", "M", 1001);
        String expected = "1 | Actor 1 | Masculino | 1001";
        assertEquals(expected, actor.getActorString());
    }

    @Test
    public void testGetActorStringFeminino() {
        Actor actor = new Actor(2, "Actor 2", "F", 1002);
        String expected = "2 | Actor 2 | Feminino | 1002";
        assertEquals(expected, actor.getActorString());
    }

    @Test
    public void testActorConstructor() {
        Actor actor = new Actor(3, "Actor 3", "M", 1003);
        assertNotNull(actor);
    }

    @Test
    public void testActorId() {
        Actor actor = new Actor(4, "Actor 4", "F", 1004);
        assertEquals(4, actor.getActorId());
    }
}
