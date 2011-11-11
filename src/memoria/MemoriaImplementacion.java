/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package memoria;

import java.util.ArrayList;

/**
 *
 * @author Johan
 */
public class MemoriaImplementacion implements MemoriaInterface {

    int [][] tableroMemoria;
    long tiempoIni;
    
    public MemoriaImplementacion() {
       tableroMemoria = new int [4][4];
       tiempoIni=0;
       
    }

    public void IniciarJuego() {
        boolean resp=false;
        int posicion_random=0;
       
        
        //Lista de Parejas -> {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8};
        ArrayList aux = new ArrayList ();
        for(int i=1; i<=8; i++){ // 8 Parejas
            aux.add(i);
            aux.add(i); //Se agrega una nueva pareja con el valor de i
        }

        //Desordenar las parejas en el tablero
        for (int fil=0; fil<4;fil++){
            for (int col=0; col<4; col++){
                posicion_random=(int) (Math.random() * aux.size());
               
                tableroMemoria[fil][col]=Integer.valueOf(aux.get(posicion_random).toString());
                aux.remove(posicion_random);
                System.out.print(tableroMemoria[fil][col]+" ");
            }
            System.out.println(" ");
        }

         //Tomamos el tiempo actual en milisegundos.
         tiempoIni=System.currentTimeMillis();

         resp= true;

        //return resp;
    }

    @Override
    public void Salir() {

    }

    @Override
    public int MostrarTarjeta(int fil, int col) {
        int resp=0;

        if (tableroMemoria!=null)
            if (fil>=0 && fil<4 && col>=0 && col<4)
                resp = tableroMemoria[fil][col];

        return resp;
    }

    //Devuelve el tiempo transcurrido (medida en segundos)
    @Override
    public int VerTiempo() {
        int resp=0;

        /*Convertir los milisegundos transcurridos desde que inicio el juego
          hasta el tiempo actual. */
        resp= (int)(System.currentTimeMillis()- tiempoIni)/1000;

        return resp;
    }

//    public static void main(String[] args) {
//        
//        MemoriaImplementacion obj = new MemoriaImplementacion();
//        obj.IniciarJuego();
//   }
}
