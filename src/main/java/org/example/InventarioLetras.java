package org.example;

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
    public char encriptarCesar(char letra) {
        return encriptarCesarAuxiliar(letra, 3);
    }

    public char desencriptarCesar(char letra) {
        return encriptarCesarAuxiliar(letra, -3);
    }

    public String encriptarPalabra(String palabra, int desplazamiento) {
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            resultado += encriptarCesarAuxiliar(palabra.charAt(i), desplazamiento);
        }
        return resultado;
    }

    public String desencriptarPalabra(String palabra, int desplazamiento) {
        return encriptarPalabra(palabra, -desplazamiento);
    }

    private char encriptarCesarAuxiliar(char letra, int desplazamiento) {
        if (letra >= 'a' && letra <= 'z') {
            return (char) ('a' + (letra - 'a' + desplazamiento % 26 + 26) % 26);
        } else if (letra >= 'A' && letra <= 'Z') {
            return (char) ('A' + (letra - 'A' + desplazamiento % 26 + 26) % 26);
        }
        return letra;
    }
    public InventarioLetras add(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras();
        for (int i = 0; i < 26; i++) {
            int suma = this.inventario.get(i) + otro.inventario.get(i);
            nuevo.inventario.set(i, suma);
            if (suma > 0) {
                nuevo.nonZeroCount++;
            }
        }
        nuevo.totalCount = this.totalCount + otro.totalCount;
        return nuevo;
    }

    public InventarioLetras subtract(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras();
        for (int i = 0; i < 26; i++) {
            int resta = this.inventario.get(i) - otro.inventario.get(i);
            if (resta < 0) {
                return null;
            }
            nuevo.inventario.set(i, resta);
            if (resta > 0) {
                nuevo.nonZeroCount++;
            }
        }
        nuevo.totalCount = this.totalCount - otro.totalCount;
        return nuevo;
    }

    public InventarioLetras amplifies(int n) {
        if (n < 0) {
            return null;
        }
        InventarioLetras nuevo = new InventarioLetras();
        for (int i = 0; i < 26; i++) {
            int multiplicacion = this.inventario.get(i) * n;
            nuevo.inventario.set(i, multiplicacion);
            if (multiplicacion > 0) {
                nuevo.nonZeroCount++;
            }
        }
        nuevo.totalCount = this.totalCount * n;
        return nuevo;
    }
}


