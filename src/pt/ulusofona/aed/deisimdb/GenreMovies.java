package pt.ulusofona.aed.deisimdb;

public class GenreMovies {
    private int genreId;
    private int movieId;

    public GenreMovies(int genreId, int movieId) {
        this.genreId = genreId;
        this.movieId = movieId;
    }

    public int getGenreId() {
        return this.genreId;
    }

    public int getMovieId() {
        return this.movieId;
    }
}
