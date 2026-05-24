import java.util.ArrayList;

public class InventarioLetras {
    private ArrayList<Integer> inventario;
    private int totalCount;
    private int nonZeroCount;

    public InventarioLetras(String data) {
        inventario = new ArrayList<Integer>();

        for (int i = 0; i < 26; i++) {
            inventario.add(0);
        }

        totalCount = 0;
        nonZeroCount = 0;

        data = data.toLowerCase();

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);

            if (c >= 'a' && c <= 'z') {
                int indice = c - 'a';
                int valorActual = inventario.get(indice);

                inventario.set(indice, valorActual + 1);
                totalCount++;

                if (valorActual == 0) {
                    nonZeroCount++;
                }
            }
        }
    }

    private InventarioLetras() {
        inventario = new ArrayList<Integer>();
        for (int i = 0; i < 26; i++) {
            inventario.add(0);
        }
        totalCount = 0;
        nonZeroCount = 0;
    }
    public int get(char letra) {
        String temporal = String.valueOf(letra).toLowerCase();
        char letraMinuscula = temporal.charAt(0);

        if (letraMinuscula < 'a' || letraMinuscula > 'z') {
            throw new IllegalArgumentException("El caracter no es una letra valida.");
        }
        return inventario.get(letraMinuscula - 'a');
    }

    public void set(char letra, int valor) {
        String temporal = String.valueOf(letra).toLowerCase();
        char letraMinuscula = temporal.charAt(0);

        if (letraMinuscula < 'a' || letraMinuscula > 'z' || valor < 0) {
            throw new IllegalArgumentException("Caracter invalido o valor negativo.");
        }

        int indice = letraMinuscula - 'a';
        int valorAnterior = inventario.get(indice);

        inventario.set(indice, valor);

        totalCount += (valor - valorAnterior);

        if (valorAnterior == 0 && valor > 0) {
            nonZeroCount++;
        } else if (valorAnterior > 0 && valor == 0) {
            nonZeroCount--;
        }
    }

    public int size() {
        return totalCount;
    }

    public boolean isEmpty() {
        return nonZeroCount == 0;
    }

    public String toString() {
        String resultado = "[";
        for (int i = 0; i < 26; i++) {
            int cantidad = inventario.get(i);
            for (int j = 0; j < cantidad; j++) {
                resultado += (char) ('a' + i);
            }
        }
        resultado += "]";
        return resultado;
    }


