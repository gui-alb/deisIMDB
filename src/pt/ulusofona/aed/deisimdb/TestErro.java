package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

class TestErro {
    //ta mal ainda, to so verificando se o log ta registrando os erros
    @BeforeEach
    void setUp() {
        Main.actorList.clear();
        Main.directorList.clear();
        Main.genreList.clear();
        Main.movieList.clear();
        Main.votesList.clear();
        Main.genreMoviesList.clear();
        Main.logsList.clear();
    }

    @Test
    void testRecebeFicheirosComErros() {

        boolean parseOk = Main.parseFiles(new File("test_files_error"));
        assertTrue(parseOk, "Falha no parseFiles.");

        ArrayList<String> errorLogs = Main.getObjects(TipoEntidade.INPUT_INVALIDO);


        assertFalse(errorLogs.isEmpty(), "A lista de logs de erro não deveria estar vazia.");

        for (String log : errorLogs) {
            String[] parts = log.split("\\|");
            assertFalse(parts.length != 4, "Log de erro deve ter 4 partes.");

            int primeiraLinhaComErro = Integer.parseInt(parts[3].trim());
            assertNotEquals(-1, primeiraLinhaComErro, "Alguma linha deve apresentar erro.");
        }
    }
}