package pt.ulusofona.aed.deisimdb;
import java.util.ArrayList;
import java.util.List;


public class Genre {
    private int genreId;
    private String genreName;

    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public int getGenreId() {
        return this.genreId;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public String getGenreString() {
        return genreId + " | " + genreName;
    }
}
