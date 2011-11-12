// cambios

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;

import java.net.*;
import java.io.*;

/**
 *
 * @author diegofernandodominguez
 */
public class MemoriaTCPCliente implements MemoriaInterface {

    DataOutputStream out;
    DataInputStream in;
    Socket sock;
    String host;
    int port;

    public MemoriaTCPCliente(String serverhost, int port) {
        out = null;
        in = null;

        this.host = serverhost;
        this.port = port;

        try {
            sock = new Socket(InetAddress.getByName(host), port);
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String enviarMensaje(String msg) {
        System.out.println("Peticion: " + msg);

        try {
            out.writeUTF(msg);
            out.flush();
            msg = in.readUTF();
            System.out.println("Respuesta: " + msg);

        } catch (IOException e) {
            System.out.println("Se ha producido un error de lectura-escritura");
        }
        return msg;
    }

    @Override
    public void IniciarJuego() {
        enviarMensaje("INICIARJUEGO");
    }

    @Override
    public void Salir() {
        enviarMensaje("SALIR");
    }

    @Override
    public int MostrarTarjeta(int fil, int col) {
        int resp;
        resp = Integer.parseInt(enviarMensaje("MOSTRARTARJETA " + fil + " " + col));
        return resp;
    }

    @Override
    public int VerTiempo() {
        int resp;
        resp = Integer.parseInt(enviarMensaje("VERTIEMPO"));
        return resp;
    }
}
