package pt.ulusofona.aed.deisimdb;
import org.junit.jupiter.api.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class TestSemErro {

    @BeforeEach
    void setup() {
        Main.actorList.clear();
        Main.directorList.clear();
        Main.genreList.clear();
        Main.movieList.clear();
        Main.votesList.clear();
        Main.genreMoviesList.clear();
        Main.logsList.clear();
    }

    @Test
    void testParseFilesAndGetObjects() {
        assertTrue(Main.parseFiles(new File("test_files")), "Falha no parseFiles");

        //verifica se getObjects retorna listas que tenham conteudo
        assertFalse(Main.getObjects(TipoEntidade.FILME).isEmpty(), "getObjects FILME deveria retornar elementos");
        assertFalse(Main.getObjects(TipoEntidade.ATOR).isEmpty(), "getObjects ATOR deveria retornar elementos");
        assertFalse(Main.getObjects(TipoEntidade.REALIZADOR).isEmpty(), "getObjects REALIZADOR deveria retornar elementos");
        assertFalse(Main.getObjects(TipoEntidade.GENERO_CINEMATOGRAFICO).isEmpty(), "getObjects GENERO_CINEMATOGRAFICO deveria retornar elementos");

        //verifica log de erros
        assertEquals(6, Main.logsList.size(), "Número de logs deve corresponder ao número de arquivos processados");
        for (Logs log : Main.logsList) {
            assertEquals(0, log.errorLines, "Pelo menos uma linha contem um erro.");
        }
    }
}