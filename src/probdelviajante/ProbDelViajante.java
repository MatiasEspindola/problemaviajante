/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probdelviajante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author matia
 */
public class ProbDelViajante {

    static List<String> soluciones = new ArrayList<>();

    private static int devolverIndice(char i) {

        switch (i) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;

            default:
                return 0;
        }
    }

    private static void Perm2(String[] elem, String act, int n, int r) {

        if (n == 0) {
            soluciones.add(act + "" + act.charAt(0));
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) {
                    Perm2(elem, act + elem[i], n - 1, r);
                }
            }
        }

    }

    public static void main(String[] args) {

        //Cantidad de nodos
        int cantidad;

        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese cantidad de nodos: ");
        cantidad = entrada.nextInt();

        while (cantidad < 2) {
            System.out.println("¡Ingrese más de 1 nodo!");
            System.out.print("Ingrese cantidad de nodos: ");
            cantidad = entrada.nextInt();
        }

        // Guarda el valor de cada ruta
        int matriz[][] = new int[cantidad + 1][cantidad + 1];

        // Me ayuda a partir la matriz para no repetir valores
        int aux = 0;

        System.out.println("");
        System.out.println("Ingrese distancia de ruta: ");
        for (int i = 1; i <= cantidad - 1; i++) {
            aux = i;
            for (int j = 2; j <= cantidad; j++) {

                // COMPARA QUE i, j NO SEAN IGUALES
                if (i != j) {
                    // SE ASEGURA QUE EL ÍNDICE j SEA MAYOR A aux QUE ES IGUAL A i,
                    // YA QUE EVITA QUE SE REPITAN LOS NODOS EJ : 3-2 y 2-3
                    if (j > aux) {
                        System.out.print("[" + i + "]" + "[" + j + "]: ");
                        matriz[i][j] = entrada.nextInt();
                        matriz[j][i] = matriz[i][j];
                    }
                } else {
                    matriz[i][j] = 0;
                    matriz[j][i] = 0;
                }

            }

            aux++;

        }

        // Separa los nodos para hacer las combinaciones
        String[] nodos = new String[cantidad];

        int opcion;

        int cantidadSoluciones = 1;

        System.out.println("");
        System.out.print("NODOS: ");
        for (int i = 0; i < cantidad; i++) {
            cantidadSoluciones = cantidadSoluciones * (i + 1);
            nodos[i] = String.valueOf(i + 1);
            System.out.print(nodos[i] + ", ");
        }
        System.out.println("");
        System.out.print("Elegir nodo: ");
        opcion = entrada.nextInt();
        while (opcion < 1 || opcion > cantidad) {
            System.out.println("");
            System.out.println("Elije un nodo válido");
            System.out.print("Elegir nodo: ");
            opcion = entrada.nextInt();
        }

        // r = rango (cantidad de nodos)
        int r = nodos.length;

        // TODAS LAS COMBINACIONES POSIBLES
        Perm2(nodos, "", cantidad, r);

        System.out.println("");

        int referencia = opcion * (cantidadSoluciones / cantidad);

        System.out.println("");
        System.out.println("Referencia: " + referencia);
        System.out.println("");

        // INDICE PARA GUARDAR LOS KM
        List<Integer> km = new ArrayList<>();

        System.out.println("Combinaciones posibles: ");
        for (int i = (referencia - (cantidadSoluciones / cantidad)); i < referencia; i++) {
            System.out.println((i + 1) + ") " + soluciones.get(i));
            System.out.println("Elementos: " + soluciones.get(i).length());
            System.out.println("Recorrido: ");

            int sumatoria = 0;
            for (int j = 1; j < cantidad + 1; j++) {

                int x = devolverIndice(soluciones.get(i).charAt(j - 1));
                int y = devolverIndice(soluciones.get(i).charAt(j));
                if (j != cantidad) {
                    System.out.print(matriz[x][y] + " + ");
                } else {
                    System.out.print(matriz[x][y]);
                }

                sumatoria += matriz[x][y];

            }
            km.add(sumatoria);
            System.out.println(" = " + km.get(i) + "km. ");
            System.out.println("");
        }

        System.out.println("");

        int min = km.get(0);

        List<Integer> caminoscortos = new ArrayList<>();

        //Guarda el camino más corto
        for (int i = (referencia - (cantidadSoluciones / cantidad)); i < referencia; i++) {
            if (min > km.get(i)) {
                min = km.get(i);
            }
        }

        //Verifica que no hayan mas de un resultado optimo
        for (int i = (referencia - (cantidadSoluciones / cantidad)); i < referencia; i++) {
            if (min == km.get(i)) {
                caminoscortos.add(i);
            }
        }

        String mensajeInicial;
        String mensajeFinal;

        if (caminoscortos.size() == 1) {
            mensajeInicial = "El siguiente recorrido:";
            mensajeFinal = "Tiene una distancia de " + min + " km. Y es el camino mas corto.";
        } else {
            mensajeInicial = "Los siguientes recorridos:";
            mensajeFinal = "Tienen una distancia de " + min + " km. Y son los caminos mas cortos.";
        }

        System.out.println(mensajeInicial);
        for (int i = 0; i < caminoscortos.size(); i++) {
            System.out.println((caminoscortos.get(i) + 1) + ") " + soluciones.get(caminoscortos.get(i)));
        }
        System.out.println(mensajeFinal);

    }
}
