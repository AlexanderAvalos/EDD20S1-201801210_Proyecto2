/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Block;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Alex
 */
public class ListaSimple {

    private Nodo primero;
    private Nodo ultimo;

    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    private boolean vacio() {
        if (primero == null) {
            return true;
        }
        return false;
    }

    public void insertar(int valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (vacio()) {
            primero = nuevo;
            ultimo = primero;
            primero.setSiguiente(null);
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
            ultimo.setSiguiente(null);
        }
    }

    public Nodo buscar(int data) {
        Nodo aux = new Nodo();
        aux = primero;
        while (aux != null) {
            if (aux.getValor() == data) {
                return aux;
            }
        }
        return null;
    }

    public void eliminar(int data) {
        Nodo aux = new Nodo();
        Nodo auxb = buscar(data);
        aux = primero;
        if (auxb == primero) {
            primero = primero.getSiguiente();
            auxb.setSiguiente(null);
        } else {
            while (aux != null) {
                if (aux.getSiguiente() == auxb) {
                    break;
                }
            }
            if (aux == ultimo) {
                aux.setSiguiente(null);
            } else {
                auxb = aux.getSiguiente();
                aux.setSiguiente(auxb.getSiguiente());
                auxb.setSiguiente(null);
            }
        }

    }

    public void printGraphvizIn() throws IOException {
        PrintWriter sw = new PrintWriter(new FileWriter("Nodos.txt"));
        sw.print("digraph G {");
        sw.println("rankdir = LR; \n");
        graficar(sw);
        sw.println("\t } \n");
        sw.close();
        String dotPath = "dot";
        String fileInputPath = "Nodos.txt";
        String fileOutputPath = "Nodos.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String[] cmd = new String[5];
        cmd[0] = dotPath;
        cmd[1] = tParam;
        cmd[2] = fileInputPath;
        cmd[3] = tOParam;
        cmd[4] = fileOutputPath;

        ExecuteCommand(cmd[0] + " " + cmd[1] + " " + cmd[2] + " " + cmd[3] + " " + cmd[4]);
    }

    static void ExecuteCommand(String _Command) throws IOException {
        Runtime.getRuntime().exec(_Command);
    }

    private void graficar(PrintWriter sw) {
        Nodo aux = new Nodo();
        aux = primero;
        while (aux != null) {
            sw.println(aux.getValor() + "[label = \"" + aux.getValor() + "\" ];");
            aux = aux.getSiguiente();
        }
        aux = primero;
        while (aux != null) {
            if (aux == primero) {
                sw.print(aux.getValor());
                aux = aux.getSiguiente();
            } else {
                sw.print(aux.getValor() + "->");
                aux = aux.getSiguiente();
            }
        }
        sw.println(";");
    }
}
