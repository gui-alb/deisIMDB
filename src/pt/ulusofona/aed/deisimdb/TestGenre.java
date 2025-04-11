package pt.ulusofona.aed.deisimdb;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestGenre {
    //talvez tenha que fazer mais testes (?????), sinceramente nao sei oq testar
    @Test
    public void targetgetGenreString(){
        Genre genre = new Genre(333, "comediaromantica");
        String expected = "333 | comediaromantica";
        assertEquals(expected, genre.getGenreString());
    }

    @Test
    public void targetGenreConstructor(){
        Genre genre = new Genre(333, "comediaromantica");
        assertNotNull(genre);
    }

    //mais testes (?????)
}
