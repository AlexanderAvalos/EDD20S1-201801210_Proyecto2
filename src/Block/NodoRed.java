/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Block;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class NodoRed extends Thread{

    private InetAddress direccion;
    private DatagramSocket socket;

    public NodoRed() {
        try {
            direccion = InetAddress.getByName("localhost");
            socket = new DatagramSocket();
        } catch (Exception e) {
        }
    }

    public InetAddress getDireccion() {
        return direccion;
    }

    public void setDireccion(InetAddress direccion) {
        this.direccion = direccion;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("iniciando");
                byte[] buffer = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socket.receive(peticion);
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);
            } catch (IOException ex) {
                Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void enviar(int puerto, String ruta) throws IOException {
        //recorrer lista simple con todos los puertos
        byte[] buffer = new byte[1024];
        buffer = ruta.getBytes();
        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puerto);
        socket.send(respuesta);
    }

    public void salir() throws IOException {
        String accion = "Eliminar";
        int puerto = 0; // recorrer lista simple
        byte[] buffer = new byte[1024];
        buffer = accion.getBytes();
        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puerto);
        socket.send(respuesta);
    }

    public void conectar(int puerto) {
        String accion = "conectar";
        byte[] buffer = new byte[1024];
        try {
            buffer = accion.getBytes();
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puerto);
            socket.send(respuesta);
            System.out.println("enviado");
        } catch (Exception e) {
            System.out.println("no enviado");
        }
    }

}
