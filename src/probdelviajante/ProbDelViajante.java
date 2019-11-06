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

    private static void Perm2(String[] elem, String act, int n, int r) {

        if (n == 0) {
            soluciones.add(act + "" + act.charAt(0));
            System.out.println(act + "" + act.charAt(0));
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

        int matriz[][] = new int[cantidad + 1][cantidad + 1];

        int aux = 0;

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
                    }
                }

            }

            aux++;

        }

        String[] nodos = new String[cantidad];

        for (int i = 0; i < cantidad; i++) {
            nodos[i] = String.valueOf(i + 1);
        }

        int r = nodos.length;

        System.out.println("");

        Perm2(nodos, "", cantidad, r);

        System.out.println("");

        for (int i = 1; i <= cantidad - 1; i++) {
            aux = i;
            for (int j = 2; j <= cantidad; j++) {

                // COMPARA QUE i, j NO SEAN IGUALES
                if (i != j) {
                    // SE ASEGURA QUE EL ÍNDICE j SEA MAYOR A AUXILIAR QUE ES IGUAL A i,
                    // YA QUE EVITA QUE SE REPITAN LOS NODOS EJ : 3-2 y 2-3
                    if (j > aux) {

                        System.out.println("[" + i + "]" + "[" + j + "]: " + matriz[i][j]);

                    }
                }

            }

            aux++;

        }

        System.out.println("");
        System.out.println("");

        // Verificar que la lista de Soluciones no este vacía ... ! * HAY QUE SEGUIR
        System.out.println("Soluciones: " + soluciones.get(0));

    }

}
