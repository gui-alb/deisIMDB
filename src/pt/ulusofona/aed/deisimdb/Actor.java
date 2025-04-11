package pt.ulusofona.aed.deisimdb;

public class Actor {
    private int actorId;
    private String actorName;
    private String actorGender;
    private int movieId;


    public Actor(int actorId, String actorName, String actorGender, int movieId) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.actorGender = actorGender;
        this.movieId = movieId;
    }

    public int getActorId() { return this.actorId;}

    public int getMovieId() {
        return this.movieId;
    }

    public String getActorString() {
        String gender = "";

        if (actorGender.equalsIgnoreCase("M")) {
            gender = "Masculino";
        } else {
            gender = "Feminino";
        }
        return actorId + " | " + actorName + " | " + gender + " | " + movieId;
    }
}
