package pt.ulusofona.aed.deisimdb;

import java.time.LocalDate;
import java.util.*;

public class ExecuteFunctions {
    public static String help(){
        return "-------------------------\n\n" +
                "Comandos disponíveis:\n" +
                "COUNT_MOVIES_MONTH_YEAR <month> <year>\n" +
                "COUNT_MOVIES_DIRECTOR <full-name>\n" +
                "COUNT_ACTORS_IN_2_YEARS <year-1> <year-2>\n" +
                "COUNT_MOVIES_BETWEEN_YEARS_WITH_N_ACTORS <year-start> <year-end> <min> <max>\n" +
                "GET_MOVIES_ACTOR_YEAR <year> <full-name>\n" +
                "GET_MOVIES_WITH_ACTOR_CONTAINING <name>\n" +
                "GET_TOP_4_YEARS_WITH_MOVIES_CONTAINING <search-string>" +
                "GET_ACTORS_BY_DIRECTOR <num> <full-name>\n" + //f
                "TOP_MONTH_MOVIE_COUNT <year>\n" + //f
                "TOP_VOTED_ACTORS <num> <year>\n" + //f
                "TOP_MOVIES_WITH_MORE_GENDER <num> <year> <gender>\n" + //f
                "TOP_MOVIES_WITH_GENDER_BIAS <num> <year>\n" + //f
                "TOP_6_DIRECTORS_WITHIN_FAMILY <year-start> <year-end>\n" + //f
                "INSERT_ACTOR <id>;<name>;<movie-id>\n" + //f
                "DISTANCE_BETWEEN_ACTORS <actor-1>,<actor-2>\n" + //f
                "HELP\n" +
                "QUIT\n" +
                "-------------------------\n";
    }

    public static String countMoviesMonthYear(int mes, int ano){
        int count = 0;

        for (Movie movie : Main.movieList) {
            LocalDate releaseDate = movie.getMovieReleaseDate();

            int anoFilme = releaseDate.getYear();
            int mesFilme = releaseDate.getMonthValue();

            if (anoFilme == ano && mesFilme == mes){
                count++;
            }
        }

        return "" + count;
    }

    public static String countMoviesDirector(String nome){
        int count = 0;
        for (Director director : Main.directorList) {
            if (Objects.equals(director.getDirectorName(), nome)){
                count++;
            }
        }
        return "" + count;
    }

    public static String countActorsIn2Years(int ano1, int ano2){
        int count = 0;

        for (Movie movie : Main.movieList) {
            int anoFilme = movie.getMovieReleaseDate().getYear();

            if (anoFilme >= ano1 && anoFilme <= ano2){
                for (Actor actor : Main.actorList) {
                    if (actor.getMovieId() == movie.getMovieId()){
                        count++;
                    }
                }
            }
        }

        return "" + count;
    }

    public static String countMoviesBetweenYearsWithNActors (int ano1, int ano2, int min, int max){
        int res = 0;
        for (Movie movie : Main.movieList) {
            int releaseYear = movie.getMovieReleaseDate().getYear();

            if (releaseYear > ano1 && releaseYear < ano2){
                int count = 0;
                for (Actor actor : Main.actorList) {
                    if (actor.getMovieId() == movie.getMovieId()){
                        count++;
                    }
                }
                if (count > min && count < max) res ++;
            }
        }

        return "" + res;
    }

    public static String getMoviesActorYear (int ano, String nome){
        Map<LocalDate, String> moviesMap = new TreeMap<>();

        for (Actor actor : Main.actorList) {
            if (Objects.equals(actor.getActorName(), nome)){
                for (Movie movie : Main.movieList) {
                    if (movie.getMovieReleaseDate().getYear() == ano) {
                        if (actor.getMovieId() == movie.getMovieId()){
                            moviesMap.put(movie.getMovieReleaseDate(), movie.getMovieName());
                        }
                    }
                }
            }
        }


        if (moviesMap.isEmpty()){
            return "No results";
        } else {
            return String.join("\n", moviesMap.values());
        }
    }

    public static String getMoviesWithActorContaining(String nome){
        ArrayList<String> lista = new ArrayList<>();

        for (Actor actor : Main.actorList) {
            if (actor.getActorName().toLowerCase().contains(nome.toLowerCase())){
                for (Movie movie : Main.movieList) {
                    if (actor.getMovieId() == movie.getMovieId()){
                        lista.add(movie.getMovieName());
                    }
                }
            }
        }
        if (lista.isEmpty()){lista.add("No results");} else {Collections.sort(lista);}

        return String.join("\n", lista);
    }

    public static String getTop4YearsWithMovieContaining(String parteNome){
        List<Pair> list = new ArrayList<>();

        for (Movie movie : Main.movieList) {
            if (movie.getMovieName().contains(parteNome)){
                int ano = movie.getMovieReleaseDate().getYear();
                boolean exist = false;
                for (Pair pair : list) {
                    if (pair.getValor1() == ano){
                        pair.valor2++;
                        exist = true;
                    }
                }
                if (!exist){
                    list.add(new Pair(ano, 1));
                }
            }
        }

        if (list.isEmpty()){
            return "No results";
        }

        list.sort(Comparator.comparing(Pair::getValor2).reversed().thenComparing(Pair::getValor1));
        StringBuilder res = new StringBuilder();
        for (Pair pair : list) {
            res.append(pair.valor1).append(":").append(pair.valor2).append("\n");
        }
        return res.toString();
    }

    public static String getActorsByDirector(int nVezes, String nome){


        return "";
    }
}
