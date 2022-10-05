/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioBueno;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alejandro Alvarez Salas
 */
public class ejercicioCambio {

    public static boolean enEjecucion = true;
    public static boolean valorCorrecto = true;
    public static String basurero = "";
    public static Scanner entrada = new Scanner(System.in);
    public static int filas;
    public static int columnas;
    public static int codigoAccion;
    public static int[][] tabla = new int[3][3];

    /**
     * Ejecuta el programa principal
     * @param args the command line arguments
     * @see muestraMenu()
     * @see ordenaTabla()
     * @see muestraTabla()
     */
    public static void main(String[] args) {
        while (enEjecucion) {
            muestraMenu();
            do {
                try {
                    codigoAccion = entrada.nextInt();

                    valorCorrecto = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Valor erróneo");
                    muestraMenu();
                    entrada.next();
                    valorCorrecto = false;
                }
            } while (!valorCorrecto);
            cosechaInformacion(codigoAccion);
            if (enEjecucion) {
                ordenaTabla(tabla);
                muestraTabla(tabla);
            }
        }
    }

    /**
     * Llena una tabla reccibida por parámetros con valores aleatorios
     * @param tabla la array sobre la que trabaja
     */
    public static void llenaTabla(int[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                tabla[i][j] = (int) (Math.random() * 10 + 1);
            }
        }
    }

    /**
     * Llena una tabla reccibida por parámetros con valores introduidos a mano
     * @param tabla la array sobre la que trabaja
     */
    public static void llenaTablaAMano(int[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                do {
                    try {
                        System.out.print((i * tabla[i].length + j) + ": ");
                        tabla[i][j] = entrada.nextInt();
                        valorCorrecto = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Valor erróneo, Introduzca un valor válido");
                        entrada.next();
                        valorCorrecto = false;
                    }
                } while (!valorCorrecto);
            }
        }
    }

    /**
     * Muestra una tabla reccibida por parámetros con valores aleatorios
     * @param tabla la array sobre la que trabaja
     */
    public static void muestraTabla(int[][] tabla) {

        System.out.println("\n Resultado: ");

        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                System.out.print("|" + tabla[i][j]);
            }
            System.out.println("\n");
        }
    }

    /**
     * Ordena una tabla recorriendola e intercambiando el valor de la posición actual con el menor de los restantes en la tabla
     * @param tabla la array sobre la que trabaja
     * @see intercambiaNumeros()
     */
    public static void ordenaTabla(int[][] tabla) {
        int[] indice = new int[2];
        int[] posicion = new int[2];
        colocaPrimero(tabla);

        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                posicion[0] = i;
                posicion[1] = j;
                indice = localizaPosicion(tabla[i][j], posicion, tabla);
                intercambiaNumeros(indice, posicion, tabla);
            }
        }
    }
    
    /**
     * Localiza el indice del menor de los valores de un array inferiores a otro valor recibido por parámetros
     * @param tabla array sobre el que trabaja
     * @param valor valor a comparar con el resto del array
     * @param posicion posición del valor a comparar con el resto en la tabla recibida
     * @return el indice del menor de los valores inferiores al recibido por parámetros en formato array
     */
    public static int[] localizaPosicion(int valor, int[] posicion, int[][] tabla) {
        //por defecto indice vale la ultima posición
        int[] indice = new int[2];
        indice[0] = tabla.length - 1;
        indice[1] = tabla[0].length - 1;
        int aux = tabla[tabla.length - 1][tabla[tabla.length - 1].length - 1];
        //en cuanto encuentra una posición con un valor superior asume que ese es su puesto
        for (int i = tabla.length - 1; i >= posicion[0]; i--) {
            if (i == posicion[0]) {
                for (int j = tabla[i].length - 1; j >= posicion[1]; j--) {  
                    if (tabla[i][j] < aux) {
                        aux = tabla[i][j];
                        indice[0] = i;
                        indice[1] = j; 
                    }
                }
            } else {
                for (int j = tabla[i].length - 1; j >= 0; j--) {
                    if (tabla[i][j] < aux) {
                        aux = tabla[i][j];
                        indice[0] = i;
                        indice[1] = j;
                    }
                }
            }
        }
        return indice;
    }

    /**
     * Intercambia los valores de dos posiciones de un array dado
     * @param tabla array sobre el que trabaja
     * @param indice indice de una de las posiciones a intercambiar
     * @param posicion indice de otra de las posiciones a intercambiar
     */
    public static void intercambiaNumeros(int[] indice, int[] posicion, int[][] tabla) {
        int aux = tabla[posicion[0]][posicion[1]];
        tabla[posicion[0]][posicion[1]] = tabla[indice[0]][indice[1]];
        tabla[indice[0]][indice[1]] = aux;

    }
    
    /**
     * Intercambia las posiciones del primer valor y el menor de un array dado por parámetros
     * @param tabla array sobre el que trabaja
     */
    public static void colocaPrimero(int[][] tabla) {
        int auxiliar = tabla[0][0];
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                if (tabla[i][j] < auxiliar) {
                    auxiliar = tabla[i][j];
                    tabla[i][j] = tabla[0][0];
                    tabla[0][0] = auxiliar;
                }
            }
        }
    }
    
    /**
     * Muestra el menú de elección de opciones de usuario
     */
    public static void muestraMenu() {
        System.out.println("Decida la opción que desea: ");
        System.out.println("-----------------------");
        System.out.println("1 - valores aleatorios y tamaño fijo (3x3)");
        System.out.println("2 - valores aleatorios y tamaño a elegir");
        System.out.println("3 - valores y tamaño a elegir");
        System.out.println("4 - Salir");
        System.out.println("-----------------------");
    }

    /**
     * Ejecuta un código u otro en función de un código de usuario recibido por parámetros
     * @param codigo código de acción de ususario
     */
    public static void cosechaInformacion(int codigo) {
        switch (codigo) {
            case 1:
                llenaTabla(tabla);
                break;
            case 2:
                System.out.print("Introduzca el nº de filas: ");
                do {
                    try {
                        filas = entrada.nextInt();
                        valorCorrecto = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Valor erróneo, Introduzca un valor válido");
                        entrada.next();
                        valorCorrecto = false;
                    }
                } while (!valorCorrecto);
                System.out.print("Introduzca el nº de columnas: ");
                do {
                    try {
                        columnas = entrada.nextInt();
                        valorCorrecto = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Valor erróneo, Introduzca un valor válido");
                        entrada.next();
                        valorCorrecto = false;
                    }
                } while (!valorCorrecto);
                tabla = new int[filas][columnas];
                llenaTabla(tabla);
                break;    
            case 3:
                System.out.print("Introduzca el nº de filas: ");
                do {
                    try {
                        filas = entrada.nextInt();
                        valorCorrecto = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Valor erróneo, Introduzca un valor válido");
                        entrada.next();
                        valorCorrecto = false;
                    }
                } while (!valorCorrecto);
                System.out.print("Introduzca el nº de columnas: ");
                do {
                    try {
                        columnas = entrada.nextInt();
                        valorCorrecto = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Valor erróneo, Introduzca un valor válido");
                        entrada.next();
                        valorCorrecto = false;
                    }
                } while (!valorCorrecto);
                tabla = new int[filas][columnas];
                llenaTablaAMano(tabla);
                break;
            case 4:
                System.out.println("Adios");
                enEjecucion = false;
                break;
            default:
                System.out.println("\n Código erróneo");
                muestraMenu();
                do {
                    try {
                        codigoAccion = entrada.nextInt();
                        valorCorrecto = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Valor erróneo, Introduzca un valor válido");
                        entrada.next();
                        valorCorrecto = false;
                    }
                } while (!valorCorrecto);
                cosechaInformacion(codigoAccion);
        }

    }

}
