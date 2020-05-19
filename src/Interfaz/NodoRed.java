/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Block.Nodo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class NodoRed extends Thread {

    private InetAddress direccion;
    private DatagramSocket socket;

    public NodoRed() {
        try {
            direccion = InetAddress.getByName("localhost");
            socket = new DatagramSocket();
        } catch (SocketException | UnknownHostException e) {
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
                byte[] buffer = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socket.receive(peticion);
                String mensaje = new String(peticion.getData());
                JOptionPane.showMessageDialog(null, mensaje);
            } catch (IOException ex) {
                Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void enviar(String ruta) throws IOException {
        Nodo aux = Estructuras.getLista().getPrimero();
        while (aux != null) {
            byte[] buffer = new byte[1024];
            buffer = ruta.getBytes();
            if (aux.getSiguiente() != null) {
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, aux.getSiguiente().getValor());
                socket.send(respuesta);
            }
            aux = aux.getSiguiente();
        }

    }

    public void salir(int port) throws IOException {
        String accion = "Eliminar";
        byte[] buffer = new byte[1024];
        buffer = accion.getBytes();
        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, port);
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
