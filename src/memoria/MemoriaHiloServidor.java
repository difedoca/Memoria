/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author diegofernandodominguez
 */
class MemoriaHiloServidor extends Thread implements Runnable {

    MemoriaImplementacion memoria;
    DataInputStream dis;
    DataOutputStream dos;
    Socket sock = null;

    public MemoriaHiloServidor(Socket sock) {
        memoria = null;
        dis = null;
        dos = null;
        this.sock = sock;

        memoria = new MemoriaImplementacion();

        try {
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String demux(String msg) {

        String vec[] = msg.split(" ");
        String retval = "NO";

        if (vec[0].equals("INICIARJUEGO")) {
            memoria.IniciarJuego();
            return "OK";
        }
        if (vec[0].equals("MOSTRARTARJETA")) {
            retval = "" + memoria.MostrarTarjeta(Integer.parseInt(vec[1]), Integer.parseInt(vec[2]));
        }

        if (vec[0].equals("SALIR")) {
            memoria.Salir();
            retval = "OK";
        }
        if (vec[0].equals("VERTIEMPO")) {
            retval = ""+memoria.VerTiempo();
        }
        
        return retval;
    }
    
    public void run() {
        String msg = "";
        String resp = null;
        System.out.println("Hilo ejecutando..");
        
        try {
            for (; !msg.equals("SALIR");) {
                dos.flush();
                msg = dis.readUTF();
                resp = demux(msg);
                System.out.println("Peticion: "+msg);
                System.out.println("Respuesta: "+resp);
                dos.writeUTF(resp);
                System.err.println("Desde: "+sock.getInetAddress().toString());
                
            }
        } catch (Exception e) {
        }
    }

}
