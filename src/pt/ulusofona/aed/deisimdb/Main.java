package pt.ulusofona.aed.deisimdb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Main {

    public static ArrayList<Actor> actorList = new ArrayList<>();
    public static ArrayList<Director> directorList = new ArrayList<>();
    public static ArrayList<Genre> genreList = new ArrayList<>();
    public static ArrayList<Movie> movieList = new ArrayList<>();
    public static ArrayList<MovieVotes> votesList = new ArrayList<>();
    public static ArrayList<GenreMovies> genreMoviesList = new ArrayList<>();
    public static ArrayList<Logs> logsList = new ArrayList<>();

    public static Boolean parseFiles(File folder) {
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        ArrayList<String> processingOrder = new ArrayList<>();
        processingOrder.add("movies.csv");
        processingOrder.add("actors.csv");
        processingOrder.add("directors.csv");
        processingOrder.add("genres.csv");
        processingOrder.add("genres_movies.csv");
        processingOrder.add("movie_votes.csv");

        if (files == null) {
            return false;
        }

        actorList.clear();
        directorList.clear();
        genreList.clear();
        movieList.clear();
        votesList.clear();
        genreMoviesList.clear();
        logsList.clear();

        for (String targetFile : processingOrder) {
            File file = null;
            for (File f : files) {
                if (f.getName().equalsIgnoreCase(targetFile)) {
                    file = f;
                    break;
                }
            }
            if (file == null) {
                System.out.println("Warning: File not found in folder: " + targetFile);
                continue;
            }

            String fileName = file.getName();
            int count = 0;
            Logs logger = new Logs(fileName);

            try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // Skip header
                }

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] values = line.split(",");
                    count++;

                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }

                    try {
                        boolean movieFound = false;
                        boolean skipAdding = false;

                        switch (fileName) {
                            case "actors.csv": {
                                if (values.length != 4) {
                                    throw new Exception("argument count error (" + values.length + ")"); //não é isto
                                }
                                int actorId = Integer.parseInt(values[0]);
                                String actorName = values[1];
                                String actorGender = values[2];
                                int actorMovieId = Integer.parseInt(values[3]);

                                if (!actorGender.equalsIgnoreCase("M") && !actorGender.equalsIgnoreCase("F")) {
                                    //throw new Exception("invalid gender error: " + actorGender);
                                }

//                                movieFound = false;
//                                for (Movie m : movieList) {
//                                    if (m.getMovieId() == actorMovieId) {
//                                        movieFound = true;
//                                        break;
//                                    }
//                                }
//                                if (!movieFound) {
//                                    throw new Exception("invalid movie id for actor: " + actorMovieId); //não é isto
//                                }
//                                for (Actor a : actorList) {
//                                    if (a.getActorId() == actorId && a.getMovieId() == actorMovieId) {
//                                        throw new Exception("duplicate actor error: " + actorId + "/" + actorMovieId); //não é isto
//                                    }
//                                }
                                actorList.add(new Actor(actorId, actorName, actorGender, actorMovieId));
                                break;
                            }

                            case "directors.csv": {
                                if (values.length != 3) {
                                    throw new Exception("argument count error (" + values.length + ")");
                                }
                                int directorId = Integer.parseInt(values[0]);
                                String directorName = values[1];
                                int directorMovieId = Integer.parseInt(values[2]);
                                movieFound = false;
//                                for (Movie m : movieList) {
//                                    if (m.getMovieId() == directorMovieId) {
//                                        movieFound = true;
//                                        break;
//                                    }
//                                }
//                                if (!movieFound) {
//                                    throw new Exception("invalid movie id for director: " + directorMovieId);
//                                }
//                                for (Director d : directorList) {
//                                    if (d.getDirectorId() == directorId && d.getMovieId() == directorMovieId) {
//                                        throw new Exception("duplicate director error: " + directorId + "/" + directorMovieId);
//                                    }
//                                }
                                directorList.add(new Director(directorId, directorName, directorMovieId));
                                break;
                            }

                            case "genres.csv": {
                                if (values.length != 2) {
                                    throw new Exception("argument count error (" + values.length + ")");
                                }
                                int genreId = Integer.parseInt(values[0]);
                                String genreName = values[1];
                                for (Genre g : genreList) {
                                    if (g.getGenreId() == genreId) {
                                        throw new Exception("duplicate genre error: " + genreId);
                                    }
                                }
                                for (Genre g : genreList) {
                                    if (g.getGenreName().equals(genreName)) {
                                        throw new Exception("duplicate genre name error: " + genreName);
                                    }
                                }
                                genreList.add(new Genre(genreId, genreName));
                                break;
                            }

                            case "movies.csv": {
                                if (values.length != 5) {
                                    throw new Exception("argument count error (" + values.length + ")");
                                }
                                int movieId = Integer.parseInt(values[0]);
                                String movieName = values[1];
                                double movieDuration = Double.parseDouble(values[2]);
                                int movieBudget = Integer.parseInt(values[3]);
                                String dateString = values[4];
                                String[] parts = dateString.split("-");
                                if (parts.length != 3) {
                                    throw new Exception("invalid date format: " + dateString);
                                }
                                int day = Integer.parseInt(parts[0]);
                                int month = Integer.parseInt(parts[1]);
                                int year = Integer.parseInt(parts[2]);
                                LocalDate movieReleaseDate = LocalDate.of(year, month, day);

//                                boolean idExists = false;
//                                for (Movie m : movieList) {
//                                    if (m.getMovieId() == movieId) {
//                                        idExists = true;
//                                        break;
//                                    }
//                                }
//
//                                if (idExists) {
//                                    skipAdding = true;
//                                } else {
//
//                                }
                                movieList.add(new Movie(movieId, movieName, movieDuration, movieBudget, movieReleaseDate));
                                break;
                            }

                            case "movie_votes.csv": {
                                if (values.length != 3) {
                                    throw new Exception("argument count error (" + values.length + ")");
                                }
                                int mvMovieId = Integer.parseInt(values[0]);
//                                movieFound = false;
//                                for (Movie m : movieList) {
//                                    if (m.getMovieId() == mvMovieId) {
//                                        movieFound = true;
//                                        break;
//                                    }
//                                }
//                                if (!movieFound) {
//                                    throw new Exception("invalid movie id for movie votes: " + mvMovieId);
//                                }
//                                for (MovieVotes mv : votesList) {
//                                    if (mv.getMovieId() == mvMovieId) {
//                                        throw new Exception("duplicate movieId error for votes: " + mvMovieId);
//                                    }
//                                }
                                double movieRating = Double.parseDouble(values[1]);
                                int movieVotes = Integer.parseInt(values[2]);
                                MovieVotes movieVotesObj = new MovieVotes(mvMovieId, movieRating, movieVotes);
                                votesList.add(movieVotesObj);
                                break;
                            }

                            case "genres_movies.csv": {
                                if (values.length != 2) {
                                    throw new Exception("argument count error (" + values.length + ")");
                                }
                                int gmGenreId = Integer.parseInt(values[0]);
                                int gmMovieId = Integer.parseInt(values[1]);
//                                movieFound = false;
//                                for (Movie m : movieList) {
//                                    if (m.getMovieId() == gmMovieId) {
//                                        movieFound = true;
//                                        break;
//                                    }
//                                }
//                                if (!movieFound) {
//                                    throw new Exception("invalid movie id for genres_movies: " + gmMovieId);
//                                }
//                                boolean genreFound = false;
//                                for (Genre g : genreList) {
//                                    if (g.getGenreId() == gmGenreId) {
//                                        genreFound = true;
//                                        break;
//                                    }
//                                }
//                                if (!genreFound) {
//                                    throw new Exception("invalid genre id for genres_movies: " + gmGenreId);
//                                }
//                                for (GenreMovies gm : genreMoviesList) {
//                                    if (gm.getGenreId() == gmGenreId && gm.getMovieId() == gmMovieId) {
//                                        throw new Exception("duplicate genre_movie entry error: " + gmGenreId + "/" + gmMovieId);
//                                    }
//                                }
                                GenreMovies genreMovies = new GenreMovies(gmGenreId, gmMovieId);
                                genreMoviesList.add(genreMovies);
                                break;
                            }

                            default: {
                                break;
                            }
                        }

                        logger.addOkLine();

                    } catch (Exception e) {
                        logger.addErrorLine(count);
                    }

                }

                System.out.println("Parsed " + logger.getOkLines() + " valid entries, encountered " + logger.getErrorLines() + " errors in " + fileName);

            } catch (Exception e) {
                System.out.println("Erro fatal ao processar o ficheiro " + fileName + ": " + e.getMessage());
                logsList.add(logger);
                return false;
            }

            logsList.add(logger);
        }

        return true;
    }


    public static ArrayList<String> getObjects(TipoEntidade tipo) {
        ArrayList<String> objects = new ArrayList<>();

        switch (tipo) {
            case FILME: {
                for (Movie movie : movieList) {
                    objects.add(movie.getMovieString());
                }
                break;
            }
            case ATOR: {
                for (Actor actor : actorList) {
                    objects.add(actor.getActorString());
                }
                break;
            }
            case GENERO_CINEMATOGRAFICO: {
                for (Genre genre : genreList) {
                    objects.add(genre.getGenreString());
                }
                break;
            }
            case REALIZADOR: {
                for (Director director : directorList) {
                    objects.add(director.getDirectorString());
                }
                break;
            }
            case INPUT_INVALIDO: {
                for (Logs log : logsList) {
                    objects.add(log.getLogString());
                }
                break;
            }
        }
        return objects;
    }

    public static Result execute(String command){
        String[] partesComando = command.trim().split(" ");

        Result result = new Result();

        if (partesComando.length == 0){
            result.success = false;
            result.error = "Nenhum comando inserido"; //ver dps qual o output certo
            return result;
        }

        String comando = partesComando[0];
        result.success = true;

        switch (comando){
            case "HELP": {
                result.result = ExecuteFunctions.help();
                break;
            }
            case "COUNT_MOVIES_MONTH_YEAR": {
                int mes = Integer.parseInt(partesComando[1]);
                int ano = Integer.parseInt(partesComando[2]);
                result.result = ExecuteFunctions.countMoviesMonthYear(mes, ano);
                break;
            }
            case "COUNT_MOVIES_DIRECTOR": {
                String nomeCompleto = partesComando[1] + " " + partesComando[2];
                result.result = ExecuteFunctions.countMoviesDirector(nomeCompleto);
                break;
            }
            case "COUNT_ACTORS_IN_2_YEARS": {
                int ano1 = Integer.parseInt(partesComando[1]);
                int ano2 = Integer.parseInt(partesComando[2]);
                result.result = ExecuteFunctions.countActorsIn2Years(ano1, ano2);
                break;
            }
            case "COUNT_MOVIES_BETWEEN_YEARS_WITH_N_ACTORS": {
                int ano1 = Integer.parseInt(partesComando[1]);
                int ano2 = Integer.parseInt(partesComando[2]);

                int min = Integer.parseInt(partesComando[3]);
                int max = Integer.parseInt(partesComando[4]);

                result.result = ExecuteFunctions.countMoviesBetweenYearsWithNActors(ano1, ano2, min, max);
                break;
            }
            case "GET_MOVIES_ACTOR_YEAR":{
                int ano = Integer.parseInt(partesComando[1]);
                String nomeCompleto = partesComando[2] + " " + partesComando[3];
                result.result = ExecuteFunctions.getMoviesActorYear(ano, nomeCompleto);

                break;
            }
            case "GET_MOVIES_WITH_ACTOR_CONTAINING":{
                result.result = ExecuteFunctions.getMoviesWithActorContaining(partesComando[1]);
                break;
            }
            case "GET_TOP_4_YEARS_WITH_MOVIES_CONTAINING":{
                result.result = ExecuteFunctions.getTop4YearsWithMovieContaining(partesComando[1]);
                break;
            }
            case "GET_ACTORS_BY_DIRECTOR":{
                String fullName = partesComando[2] + " " + partesComando[3];
                result.result = ExecuteFunctions.getActorsByDirector(Integer.parseInt(partesComando[1]), fullName);

                break;
            }
            case null, default:{
                result.success = false;
                result.error = "deu erro chefe";
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao deisIMDB");

        File testFolder = new File("test_files");
        System.out.println("Parsing files in folder: " + testFolder.getAbsolutePath());

        long startTime = System.currentTimeMillis();
        boolean parseOk = parseFiles(testFolder);
        if (!parseOk) {
            System.out.println("Erro ao processar os ficheiros.");
            return;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Ficheiros processados com sucesso em: " + (endTime - startTime) + " ms");

        Result result = execute("HELP");
        System.out.println(result.result);

        Scanner in = new Scanner(System.in);
        String line;

        do {
            System.out.print("> ");
            line = in.nextLine();

            if (line != null && !line.equals("QUIT")){
                startTime = System.currentTimeMillis();
                result = execute(line);
                endTime = System.currentTimeMillis();

                if (!result.success){
                    System.out.println("Erro: " + result.error);
                } else {
                    System.out.println(result.result);
                    System.out.println("Demorou " + (endTime - startTime) + "ms");
                }
            }
        } while (line != null && !line.equals("QUIT"));
    }
}