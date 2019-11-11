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
                }else{
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

        int km[] = new int[referencia];
        
        
        System.out.println("Combinaciones posibles: ");
        for (int i = (referencia - (cantidadSoluciones / cantidad)); i < referencia; i++) {
            System.out.println((i + 1) + ") " + soluciones.get(i));
            System.out.println("Elementos: " + soluciones.get(i).length());
            for (int j = 1; j < soluciones.size(); j++) {
                
                    if(j == soluciones.get(i).length() - 1){
                        System.out.print(matriz[j][1] + " - ");
                    }else{
                        System.out.print(matriz[j][j+1] + " - ");
                    }
                
            }
            System.out.println("");
        } 

//        System.out.println("Combinaciones posibles: ");
//        
//        
//        
//
//
//        System.out.println("");

    }

}
