/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Block;

import Biblioteca.NodoAvl;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Alex
 */
public class ListaDoble {

    private NodoDoble primero;
    private NodoDoble ultimo;

    private boolean vacio() {
        return primero == null && ultimo == null;
    }

    public void Insertar(Bloque dato) {
        NodoDoble nuevo = new NodoDoble();
        if (vacio()) {
            nuevo = new NodoDoble(dato);
            primero = nuevo;
            ultimo = primero;
        } else {
            nuevo = new NodoDoble(dato);
            ultimo.setSiguiente(nuevo);
            nuevo.setSiguiente(null);
            nuevo.setAnterior(null);
            ultimo = nuevo;
        }
    }

    public NodoDoble buscar(Bloque dato) {
        NodoDoble aux = new NodoDoble();
        aux = primero;
        if (vacio()) {
            return null;
        }
        while (aux != null) {
            if (aux.getDato() == dato) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public void Eliminar(Bloque dato) {
        NodoDoble actual = new NodoDoble();
        NodoDoble aux = buscar(dato);
        actual = primero;
        if (aux != null) {
            if (aux == primero) {
                primero = actual.getSiguiente();
                primero.setAnterior(null);
                actual.setSiguiente(null);
            } else {
                while (actual != null) {
                    if (aux == ultimo) {
                        ultimo = actual.getAnterior();
                        ultimo.setSiguiente(null);
                        actual.setAnterior(null);
                    } else {
                        actual = aux.getAnterior();
                        actual.setSiguiente(aux.getSiguiente());
                        actual = aux.getSiguiente();
                        actual.setAnterior(aux.getAnterior());
                        actual = aux;
                        actual.setSiguiente(null);
                        actual.setAnterior(null);
                    }
                }
            }
        } else {
            System.out.println("no existe");
        }
    }

    public void graficar(PrintWriter sw) {
        NodoDoble aux = new NodoDoble();
        aux = primero;
        while (aux != null) {
            sw.println(aux.getDato().data + "[label = \"" + aux.getDato().data + "\" ;");
            aux = aux.getSiguiente();
        }
        aux = primero;
        while (aux != null) {
            if (aux == primero) {
                sw.print(aux.getDato().data);
                aux = aux.getSiguiente();
            } else {
                sw.print(aux.getDato().data + "->");
                aux = aux.getSiguiente();
            }
        }
        sw.println(";");
        while(aux != null){
              if (aux == ultimo) {
                sw.print(aux.getDato().data);
                aux = aux.getSiguiente();
            } else {
                sw.print(aux.getDato().data + "->");
                aux = aux.getSiguiente();
            }
        }
    }

    public void printGraphvizIn() throws IOException {
        PrintWriter sw = new PrintWriter(new FileWriter("Bloques.txt"));
        sw.print("digraph G {");
        sw.println("rankdir = LR; \n");
        graficar(sw);
        sw.println("\t } \n");
        sw.close();
        String dotPath = "dot";
        String fileInputPath = "Bloques.txt";
        String fileOutputPath = "Bloques.png";
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
}
