package pt.ulusofona.aed.deisimdb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMovie {
    @BeforeEach
    void setUp() {
        Main.actorList.clear();
        Main.directorList.clear();
        Main.genreList.clear();
        Main.movieList.clear();
        Main.votesList.clear();
        Main.genreMoviesList.clear();
        Main.logsList.clear();
    }

    @Test
    public void targetgetMovieStringComIdMenorQueMil(){
        Main.actorList.add(new Actor(1, "sla1", "M", 1));
        Main.actorList.add(new Actor(2, "sla2", "F", 1));
        Main.actorList.add(new Actor(3, "sla3", "M", 1));

        Movie movie = new Movie(1, "sla", 0.0, 0, LocalDate.of(1979, 6, 1));
        String expected = "1 | sla | 1979-06-01 | 3";
        assertEquals(expected, movie.getMovieString());
    }

    @Test
    public void targetgetMovieStringComIdMaiorQueMil(){
        Movie movie = new Movie(1000, "sla", 0.0, 0, LocalDate.of(1979, 6, 1));
        String expected = "1000 | sla | 1979-06-01";
        assertEquals(expected, movie.getMovieString());
    }

    @Test
    public void targetgetMovieStringComIdIgualAMil(){
        Movie movie = new Movie(1000, "sla", 0.0, 0, LocalDate.of(1979, 6, 1));
        String expected = "1000 | sla | 1979-06-01";
        assertEquals(expected, movie.getMovieString());
    }

    @Test
    public void targetgetMovieStringComIdIgualAMenosUm(){
        Movie movie = new Movie(-1, "sla", 0.0, 0, LocalDate.of(1979, 6, 1));
        String expected = "-1 | sla | 1979-06-01 | 0";
        assertEquals(expected, movie.getMovieString());
    }

    @Test
    public void targetgetMovieStringComEspaços(){
        Movie movie = new Movie(0, "   sla   ", 0.0, 0, LocalDate.of(1979, 6, 1));
        String expected = "0 |    sla    | 1979-06-01 | 0";
        assertEquals(expected, movie.getMovieString());
    }
}
