/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javarepaso;

import static ejercicioBueno.ejercicioCambio.muestraTabla;

/**
 *
 * @author damm
 */
public class JavaRepaso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] tabla= new int[3][3];
        
        llenaTabla(tabla);
        muestraTabla(tabla);
        ordenaTabla(tabla);
        System.out.println("--------------------------");
        muestraTabla(tabla);
        
        
        
        
    }
    
    public static void llenaTabla(int[][] tabla){
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla.length; j++){
                tabla[i][j]=(int)(Math.random()*10+1);
                System.out.println(tabla[i][j]);
            }
        }
    }
    
    public static void muestraTabla(int[][] tabla){
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla.length; j++){
                System.out.print("|"+tabla[i][j]);
            }
            System.out.println("\n");
        }
    }
    
    public static void ordenaTabla(int[][] tabla){
        int[] indice=new int[2];
        int[] posicion= new int[2];
        //olocaPrimero(tabla);
        
        
        
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla[i].length;j++){
                System.out.println("el valor por el que voy es "+tabla[i][j]);
                posicion[0]=i;
                posicion[1]=j;
                
                indice=localizaPosicion(tabla[i][j], posicion, tabla);
                
                colocaYDesplaza(indice,posicion, tabla);
                
            }
            
            
        }
        
    }
    
    public static int[] localizaPosicion (int valor,int[] posicion, int[][] tabla){
        //por defecto indice vale la ultima posición
        int[] indice= {tabla.length-1, tabla[tabla.length-1].length-1};
        //en cuanto encuentra una posición con un valor superior asume que ese es su puesto
        for(int i=posicion[0]; i<tabla.length; i++){
            for(int j=posicion[1]; j<tabla[i].length; j++){
                //por el que voy tiene que ser menor que el valor por el que 
                if(tabla[i][j]<valor){
                    indice[0]=i;
                    indice[1]=j;
                    valor=tabla[indice[0]][indice[1]];
                    System.out.println("el indice a cambiar es "+indice[0]+indice[1]+ " y contiene "+tabla[indice[0]][indice[1]]);
                }
            }
            
        }
        
        muestraTabla(tabla);
        return indice;
    }
    
    public static void colocaYDesplaza(int[]indice,int[] posicion, int[][] tabla){
        //no necesito auxiliar para almacenar el último valor porque puedo darle la posición del valor a ordenar pero si uno para el array de ultimos
        int valorIndice=tabla[indice[0]][indice[1]];
        System.out.println("guardo el valor del indice, que es "+ tabla[indice[0]][indice[1]]);
        int valorUltimoTabla=tabla[tabla.length-1][tabla[tabla.length-1].length-1];
        int contadorUltimos=0;
        //el array de ultimos tiene las filas totales menos las que no son afectadas (menores que el indice[1]) menos uno porque el último valor no lo almacena
        int[] ultimoFila=new int[tabla.length-indice[0]];
        
        //Meto en la posición del valor a ordenar el último valor para evitar que se pierda
        tabla[indice[0]][indice[1]]=tabla[tabla.length-1][tabla[tabla.length-1].length-1];
        System.out.println("guardo"+tabla[tabla.length-1][tabla[tabla.length-1].length-1]+" en la posicion del indice");
        //ordena los valores desde el final hasta la posición del indice
        for(int i=tabla.length-1; i>=indice[0]; i--){
            
            if(i!=tabla.length-1){
                ultimoFila[contadorUltimos]=tabla[i][tabla[i].length-1];
                contadorUltimos++;
                System.out.println("guardo "+tabla[i][tabla[i].length-1] +" en el array de ultimos");
            }
            if(i==posicion[0]){
                for(int j=tabla[i].length-1; j>posicion[1]; j--){
                    tabla[i][j]=tabla[i][j-1];
                    System.out.println("desplazo hasta el indice de posicion");
                    muestraTabla(tabla);
                }
            }else{
                //los mueve todos
                for(int j=tabla[i].length-1; j>0; j--){
                    if(j!=0){
                    tabla[i][j]=tabla[i][j-1];
                    }
                    
                    System.out.println("desplazo hasta el final de la fila");
                    muestraTabla(tabla);
                }
            }
            
            
        }
        //recoloco el valor en la posición del indice
        tabla[posicion[0]][posicion[1]]=valorIndice;
        System.out.println("recoloco el valor del indice en la posicion");
        muestraTabla(tabla);
        
        //ahora recoloco los perdidos sabiendo que están en orden inverso sacandolos del array de ultimos
            System.out.println("recoloco los ultimos");
        for(int k=0; k<ultimoFila.length-1; k++){
            System.out.println(ultimoFila[k]);
            tabla[0][tabla.length-1-(1+k)]=ultimoFila[k];  
        }
        
    }
        
    
    
    public static void colocaPrimero(int[][] tabla){
        int auxiliar= tabla[0][0];
        
        for(int i=0; i<tabla.length; i++){
            for(int j=0; j<tabla.length; j++){
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
    
}
