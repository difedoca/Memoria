/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author diegofernandodominguez
 */
public class MemoriaServidor {

    ServerSocket ss;
    MemoriaHiloServidor memoriaHilo;

    public MemoriaServidor(int puerto) {
        ss = null;
        memoriaHilo = null;
        try {
            ss = new ServerSocket(puerto);

        } catch (IOException e) {
            System.out.println("Error al habilitar el puerto");
        }
    }

    public void daemon() {
        while (true) {
            try {
                System.out.println("Waiting...");
                Socket socket = ss.accept();
                System.out.println("new client");
                memoriaHilo = new MemoriaHiloServidor(socket);
                memoriaHilo.start();
            } catch (IOException iOException) {
            }
        }
    }
    
    public static void main(String[] args) {
        MemoriaServidor mserver = new MemoriaServidor(2000);
        mserver.daemon();
    }
}
