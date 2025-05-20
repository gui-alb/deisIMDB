package pt.ulusofona.aed.deisimdb;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


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

    void getAmountOfActors(int[] actorByGender) {
        for (Actor actor : Main.actorList) {
            if (actor.getMovieId() == this.movieId) {
                String gender = actor.getActorGender();
                if (Objects.equals(gender, "M")){
                    actorByGender[0]++;
                } else {
                    actorByGender[1]++;
                }
            }
        }
    }

    int getAmountOfGenres() {
        int count = 0;
        for (GenreMovies genre : Main.genreMoviesList) {
            if (genre.getMovieId() == this.movieId) {
                count++;
            }
        }
        return count;
    }

    String getGenres(){
        List<String> genres = new ArrayList<>();
        for (GenreMovies genreMovie : Main.genreMoviesList) {
            if (genreMovie.getMovieId() == this.movieId){
                for (Genre genre : Main.genreList) {
                    if (genre.getGenreId() == genreMovie.getGenreId()){
                        genres.add(genre.getGenreName());
                    }
                }
            }
        }
        Collections.sort(genres);
        return String.join(",", genres);
    }

    int getAmountOfDirectors() {
        int count = 0;
        for (Director director : Main.directorList) {
            if (director.getMovieId() == this.movieId) {
                count++;
            }
        }
        return count;
    }

    String getDirectors(){
        List<String> directors = new ArrayList<>();
        for (Director director : Main.directorList) {
            if (director.getMovieId() == this.movieId){
                directors.add(director.getDirectorName());
            }
        }
        Collections.sort(directors);
        return String.join(",", directors);
    }

    public String getMovieString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int[] actorsByGender = {0, 0};
        getAmountOfActors(actorsByGender);
        String directors = getDirectors();

        if (movieId < 1000) {
            return movieId + " | " + movieName + " | " + movieReleaseDate.format(outputFormatter) + " | " + getGenres() + " | "+ directors + " | " + actorsByGender[0] + " | " + actorsByGender[1];
        }

        return movieId + " | " + movieName + " | " + movieReleaseDate.format(outputFormatter) + " | " + getAmountOfGenres() + " | " + getAmountOfDirectors() + " | " + actorsByGender[0] + " | " + actorsByGender[1];
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