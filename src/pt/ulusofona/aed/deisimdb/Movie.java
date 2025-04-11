package pt.ulusofona.aed.deisimdb;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class Movie {
    private int movieId;
    private String movieName;
    private double movieDuration;
    private int movieBudget;
    private LocalDate movieReleaseDate;

    public Movie(int movieId, String movieName, double movieDuration, int movieBudget, LocalDate movieReleaseDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate;
    }

    int getAmountOfActors() {
        int count = 0;
        for (Actor actor : Main.actorList) {
            if (actor.getMovieId() == this.movieId) {
                count++;
            }
        }
        return count;
    }

    public String getMovieString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        if (movieId < 1000) {
            return movieId + " | " + movieName + " | " + movieReleaseDate.format(outputFormatter) + " | " + getAmountOfActors();
        }

        return movieId + " | " + movieName + " | " + movieReleaseDate.format(outputFormatter);
    }

    public int getMovieId() {
        return this.movieId;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public LocalDate getMovieReleaseDate() {
        return this.movieReleaseDate;
    }
}