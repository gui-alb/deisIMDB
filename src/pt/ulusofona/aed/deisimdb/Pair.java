package pt.ulusofona.aed.deisimdb;

public class Pair {
    String valor1;
    int valor2;

    public Pair() {
    }

    public Pair(String valor1, int valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public int getValor1int() {
        return Integer.parseInt(valor1);
    }

    public String getValor1() {
        return valor1;
    }

    public int getValor2() {
        return valor2;
    }
}
