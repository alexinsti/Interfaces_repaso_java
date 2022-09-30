/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioBueno;

import java.util.Scanner;

/**
 *
 * @author alejandro
 */
public class ejercicioCambio {
    public static Scanner entrada= new Scanner(System.in);
    public static int filas;
    public static int columnas;
    public static int codigoAccion;
    public static int[][] tabla= new int[3][3];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        muestraMenu();
        codigoAccion=entrada.nextInt();
        cosechaInformacion(codigoAccion);
        
        
        
        
        muestraTabla(tabla);
        ordenaTabla(tabla);
    }
    
    public static void llenaTabla(int[][] tabla){
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla[i].length; j++){
                tabla[i][j]=(int)(Math.random()*10+1);
                //System.out.println(tabla[i][j]);
            }
        }
    }
    
    public static void llenaTablaAMano(int[][] tabla){
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla[i].length; j++){
                tabla[i][j]=entrada.nextInt();
                //System.out.println(tabla[i][j]);
            }
        }
    }
    
    public static void muestraTabla(int[][] tabla){
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla[i].length; j++){
                System.out.print("|"+tabla[i][j]);
            }
            System.out.println("\n");
        }
    }
    
    public static void ordenaTabla(int[][] tabla){
        int[] indice=new int[2];
        int[] posicion= new int[2];
        colocaPrimero(tabla);
                
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla[i].length; j++){
                //System.out.println("posicion de tabla: "+((tabla.length*i+j)+1));
                posicion[0]=i;
                posicion[1]=j;
                //System.out.println("intercambio el "+ tabla[i][j]+ " indice: "+posicion[0]+posicion[1]);
                indice=localizaPosicion(tabla[i][j], posicion, tabla);
                intercambiaNumeros(indice, posicion, tabla);  
            }
        }
        
    }
    
    public static void adelante(int[][] tabla){
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla[i].length; j++){
                System.out.println(tabla[i][j]);
                
            }
        }
    }
    
    public static void atras(int[][] tabla){
        for(int i=tabla.length-1; i>=0; i--){
            for(int j=tabla[i].length-1; j>=0; j--){
                System.out.println(tabla[i][j]);
                
            }
        }
    }
    
    public static int[] localizaPosicion (int valor,int[] posicion, int[][] tabla){
        //por defecto indice vale la ultima posición
        int[] indice= new int[2];
        indice[0]=tabla.length-1;
        indice[1]=tabla[0].length-1;
        int aux=tabla[tabla.length-1][tabla[tabla.length-1].length-1];
        //en cuanto encuentra una posición con un valor superior asume que ese es su puesto
        for(int i=tabla.length-1; i>=posicion[0]; i--){
            
            if(i==posicion[0]){
                for(int j=tabla[i].length-1; j>=posicion[1]; j--){
                        if(tabla[i][j]<aux){
                            aux=tabla[i][j];
                            indice[0]=i;
                            indice[1]=j;
                        }
                }
            }else{
                for(int j=tabla[i].length-1; j>=0; j--){
                    if(tabla[i][j]<aux){
                            aux=tabla[i][j];
                            indice[0]=i;
                            indice[1]=j;
                        }
                }
            }
                
            
        }
        //System.out.println("con el "+tabla[indice[0]][indice[1]]+" indice: "+indice[0]+indice[1]);
        return indice;
    }
    
    public static void intercambiaNumeros(int[] indice, int[] posicion, int[][] tabla){
        int aux=tabla[posicion[0]][posicion[1]];
        
        tabla[posicion[0]][posicion[1]]=tabla[indice[0]][indice[1]];
        tabla[indice [0]][indice[1]]=aux;
        
        muestraTabla(tabla);
        
    }
    
    
    
    public static void colocaPrimero(int[][] tabla){
        int auxiliar= tabla[0][0];
        
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla[i].length; j++){
                if(tabla[i][j]<auxiliar){
                    auxiliar=tabla[i][j];
                    tabla[i][j]=tabla[0][0];
                    tabla[0][0]=auxiliar;
                    System.out.println("...............");
                    muestraTabla(tabla);
                    System.out.println("...............");
                }
            }
            
            
        }
        
    }
    
    public static void muestraMenu(){
        System.out.println("Decida la opción que desea: ");
        System.out.println("-----------------------");
        System.out.println("1 - valores aleatorios y tamaño fijo (3x3)");
        System.out.println("2 - valores aleatorios y tamaño a elegir");
        System.out.println("3 - valores y tamaño a elegir");
        System.out.println("-----------------------");
        
    }
    
    public static void cosechaInformacion(int codigo){
        
        switch(codigo){
            case 1:
                System.out.println("De acuerdo, voy a ello");
                llenaTabla(tabla);
                break;
            case 2:
                System.out.println("Introduzca el nº de filas: ");
                filas=entrada.nextInt();
                System.out.println("Introduzca el nº de columnas: ");
                columnas=entrada.nextInt();
                
                tabla=new int[filas][columnas];
                llenaTabla(tabla);
                
                break;
            case 3:
                System.out.println("Introduzca el nº de filas: ");
                filas=entrada.nextInt();
                System.out.println("Introduzca el nº de columnas: ");
                columnas=entrada.nextInt();
                
                tabla=new int[filas][columnas];
                llenaTablaAMano(tabla);
                
                break;
               
            default:
                System.out.println("Introduzca un código válido");
                muestraMenu();
                cosechaInformacion(codigo);
                
                
        }
        
    }
    
}
