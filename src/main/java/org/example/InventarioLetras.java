package org.example;

import java.util.ArrayList;
import java.util.Scanner;

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
class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el texto inicial para el inventario: ");
        String texto = scanner.nextLine();

        InventarioLetras inv = new InventarioLetras(texto);
        int opcion = 0;

        while (opcion != 8) {
            System.out.println("\n--- MENU INVENTARIO DE LETRAS ---");
            System.out.println("1. Mostrar inventario actual (toString)");
            System.out.println("2. Ver total de letras (size)");
            System.out.println("3. Verificar si esta vacio (isEmpty)");
            System.out.println("4. Obtener cantidad de una letra (get)");
            System.out.println("5. Cambiar cantidad de una letra (set)");
            System.out.println("6. Encriptar una palabra (Cesar)");
            System.out.println("7. Desencriptar una palabra (Cesar)");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.println("Inventario: " + inv.toString());
            } else if (opcion == 2) {
                System.out.println("Total de letras: " + inv.size());
            } else if (opcion == 3) {
                System.out.println("¿Esta vacio?: " + inv.isEmpty());
            } else if (opcion == 4) {
                System.out.print("Ingrese la letra a consultar: ");
                char letra = scanner.nextLine().charAt(0);
                try {
                    System.out.println("Cantidad de '" + letra + "': " + inv.get(letra));
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (opcion == 5) {
                System.out.print("Ingrese la letra a modificar: ");
                char letra = scanner.nextLine().charAt(0);
                System.out.print("Ingrese la nueva cantidad: ");
                int nuevaCantidad = scanner.nextInt();
                scanner.nextLine();
                try {
                    inv.set(letra, nuevaCantidad);
                    System.out.println("Inventario actualizado.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (opcion == 6) {
                System.out.print("Ingrese la palabra a encriptar: ");
                String palabra = scanner.nextLine();
                System.out.print("Ingrese el desplazamiento numerico: ");
                int desp = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Resultado: " + inv.encriptarPalabra(palabra, desp));
            } else if (opcion == 7) {
                System.out.print("Ingrese la palabra a desencriptar: ");
                String palabra = scanner.nextLine();
                System.out.print("Ingrese el desplazamiento numerico: ");
                int desp = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Resultado: " + inv.desencriptarPalabra(palabra, desp));
            } else if (opcion == 8) {
                System.out.println("Saliendo del programa.");
            } else {
                System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
        scanner.close();
    }
}


