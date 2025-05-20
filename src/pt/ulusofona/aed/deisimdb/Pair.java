package pt.ulusofona.aed.deisimdb;

public class Pair<K, V> {
    K valor1;
    V valor2;

    public Pair() {
    }

    public Pair(K valor1, V valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public K getValor1() {
        return valor1;
    }

    public V getValor2() {
        return valor2;
    }

    public V setValor2(V valor2){
        return this.valor2 = valor2;
    }
}
