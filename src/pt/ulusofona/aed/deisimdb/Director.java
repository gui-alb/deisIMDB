package pt.ulusofona.aed.deisimdb;
import java.util.ArrayList;

public class Director {
    private int directorId;
    private String directorName;
    private int movieId;



    public Director(int directorId, String directorName, int movieId) {
        this.directorId = directorId;
        this.directorName = directorName;
        this.movieId = movieId;
    }

    public String getDirectorString() {
        return directorId + " | " + directorName + " | " + movieId;
    }

    public int getDirectorId() {
        return this.directorId;
    }
    public String getDirectorName() {
        return this.directorName;
    }
    public int getMovieId() {
        return this.movieId;
    }
}
