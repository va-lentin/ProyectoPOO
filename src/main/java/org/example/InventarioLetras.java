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
}
