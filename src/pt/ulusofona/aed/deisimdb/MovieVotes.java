package pt.ulusofona.aed.deisimdb;

public class MovieVotes {
    private int movieId;
    private double movieRating;
    private int movieRatingCount;


    public MovieVotes(int movieId, double movieRating, int movieRatingCount) {
        this.movieId = movieId;
        this.movieRating = movieRating;
        this.movieRatingCount = movieRatingCount;
    }

    public int getMovieId() {
        return movieId;
    }

    public double getMovieRating(){
        return movieRating;
    }

}