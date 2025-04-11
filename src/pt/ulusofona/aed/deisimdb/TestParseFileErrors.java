// File: TestParseFilesErrors.java
package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class TestParseFileErrors {

    private void clearMainLists() {
        Main.actorList.clear();
        Main.directorList.clear();
        Main.genreList.clear();
        Main.movieList.clear();
        Main.votesList.clear();
        Main.genreMoviesList.clear();
        Main.logsList.clear();
    }

    private void createValidTestFiles(File folder) throws IOException {
        // Ficheiro movies.csv
        File movies = new File(folder, "movies.csv");
        try (FileWriter fw = new FileWriter(movies)) {
            fw.write("movieId,movieName,movieDuration,movieBudget,movieReleaseDate\n");
            fw.write("500,Test Movie A,120.0,1000000,01-01-2020\n");
            fw.write("1001,Test Movie B,90.0,500000,15-05-2021\n");
        }
        // Ficheiro actors.csv
        File actors = new File(folder, "actors.csv");
        try (FileWriter fw = new FileWriter(actors)) {
            fw.write("actorId,actorName,actorGender,movieId\n");
            fw.write("1,Actor A,M,500\n");
            fw.write("2,Actor B,F,500\n");
            fw.write("3,Actor C,M,1001\n");
        }
        // Ficheiro directors.csv
        File directors = new File(folder, "directors.csv");
        try (FileWriter fw = new FileWriter(directors)) {
            fw.write("directorId,directorName,movieId\n");
            fw.write("1,Director A,500\n");
            fw.write("2,Director B,1001\n");
        }
        // Ficheiro genres.csv
        File genres = new File(folder, "genres.csv");
        try (FileWriter fw = new FileWriter(genres)) {
            fw.write("genreId,genreName\n");
            fw.write("1,Action\n");
            fw.write("2,Drama\n");
        }
        // Ficheiro genres_movies.csv
        File genresMovies = new File(folder, "genres_movies.csv");
        try (FileWriter fw = new FileWriter(genresMovies)) {
            fw.write("movieId,genreId\n");
            fw.write("500, 1\n");
            fw.write("1001, 2\n");
        }
        // Ficheiro movie_votes.csv
        File votes = new File(folder, "movie_votes.csv");
        try (FileWriter fw = new FileWriter(votes)) {
            fw.write("movieId,movieRating,movieVotes\n");
            fw.write("500,7.5,100\n");
            fw.write("1001,8.0,150\n");
        }
    }

    @Test
    public void testParseFilesWithErrorInActors(@TempDir Path tempDirPath) throws IOException {
        clearMainLists();
        File folder = tempDirPath.toFile();
        createValidTestFiles(folder);
        // Introduz um erro no ficheiro actors.csv (linha com campos em falta)
        File actors = new File(folder, "actors.csv");
        try (FileWriter fw = new FileWriter(actors, true)) {
            fw.write("4,Actor D\n");
        }
        Main.parseFiles(folder);
        List<String> logs = Main.getObjects(TipoEntidade.INPUT_INVALIDO);
        boolean found = false;
        for (String log : logs) {
            if (log.startsWith("actors.csv")) {
                // Espera-se: 3 linhas válidas, 1 erro (a linha 4)
                assertEquals("actors.csv | 3 | 1 | 4", log);
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testParseFilesWithErrorInDirectors(@TempDir Path tempDirPath) throws IOException {
        clearMainLists();
        File folder = tempDirPath.toFile();
        createValidTestFiles(folder);
        // Introduz um erro no ficheiro directors.csv (linha com falta de um campo)
        File directors = new File(folder, "directors.csv");
        try (FileWriter fw = new FileWriter(directors, true)) {
            fw.write("3,Director C\n");
        }
        Main.parseFiles(folder);
        List<String> logs = Main.getObjects(TipoEntidade.INPUT_INVALIDO);
        boolean found = false;
        for (String log : logs) {
            if (log.startsWith("directors.csv")) {
                // Espera-se: 2 linhas válidas, 1 erro (linha 3)
                assertEquals("directors.csv | 2 | 1 | 3", log);
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testParseFilesWithErrorInGenres(@TempDir Path tempDirPath) throws IOException {
        clearMainLists();
        File folder = tempDirPath.toFile();
        createValidTestFiles(folder);
        // Introduz um erro no ficheiro genres.csv (linha com falta de género)
        File genres = new File(folder, "genres.csv");
        try (FileWriter fw = new FileWriter(genres, true)) {
            fw.write("3\n");
        }
        Main.parseFiles(folder);
        List<String> logs = Main.getObjects(TipoEntidade.INPUT_INVALIDO);
        boolean found = false;
        for (String log : logs) {
            if (log.startsWith("genres.csv")) {
                assertEquals("genres.csv | 2 | 1 | 3", log);
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testParseFilesWithErrorInMovies(@TempDir Path tempDirPath) throws IOException {
        clearMainLists();
        File folder = tempDirPath.toFile();
        createValidTestFiles(folder);
        // Introduz um erro no ficheiro movies.csv (linha com campos insuficientes)
        File movies = new File(folder, "movies.csv");
        try (FileWriter fw = new FileWriter(movies, true)) {
            fw.write("1\n");
        }
        Main.parseFiles(folder);
        List<String> logs = Main.getObjects(TipoEntidade.INPUT_INVALIDO);
        boolean found = false;
        for (String log : logs) {
            if (log.startsWith("movies.csv")) {
                assertEquals("movies.csv | 2 | 1 | 3", log);
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testParseFilesWithErrorInGenresMovies(@TempDir Path tempDirPath) throws IOException {
        clearMainLists();
        File folder = tempDirPath.toFile();
        createValidTestFiles(folder);
        // Introduz um erro no ficheiro genres_movies.csv (linha com campos insuficientes)
        File genresMovies = new File(folder, "genres_movies.csv");
        try (FileWriter fw = new FileWriter(genresMovies, true)) {
            fw.write("1001\n");
        }
        Main.parseFiles(folder);
        List<String> logs = Main.getObjects(TipoEntidade.INPUT_INVALIDO);
        boolean found = false;
        for (String log : logs) {
            if (log.startsWith("genres_movies.csv")) {
                assertEquals("genres_movies.csv | 2 | 1 | 3", log);
                found = true;
            }
        }
        assertTrue(found);
    }
}
