package pt.ulusofona.aed.deisimdb;
import java.util.ArrayList;

public class Logs {
    private String fileName;
    private int okLines;
    public int errorLines;

    private ArrayList<Integer> errorLinesList = new ArrayList<>();

    public Logs(String fileName) {
        this.fileName = fileName;
        this.okLines = 0;
        this.errorLines = 0;
    }

    public void addOkLine() {
        this.okLines++;
    }

    public void addErrorLine(int lineNumber) {
        this.errorLines++;
        this.errorLinesList.add(lineNumber);
    }

    public String getLogString() {
        if (errorLinesList.isEmpty()) {
            return fileName + " | " + okLines  + " | " + errorLines + " | -1";
        }

        return fileName + " | " + okLines  + " | " + errorLines + " | " + errorLinesList.get(0);
    }

    public int getOkLines() {
        return okLines;
    }

    public int getErrorLines() {
        return errorLines;
    }
}