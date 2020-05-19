/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Alex
 */
public class TablaHash {

    public NodoTabla[] Tabla;
    private int TAMTABLA;
    private int numElementos;

    public TablaHash() {
        this.TAMTABLA = 45;
        this.Tabla = new NodoTabla[TAMTABLA];
        for (int i = 0; i < TAMTABLA; i++) {
            Tabla[i] = null;
        }
        numElementos = 0;
    }

    public boolean existe(int id) {
        NodoTabla actual = Tabla[id];
        if (actual != null) {
            if (actual.eliminado) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public void insertar(int id, Usuario valor) {
        if (!existe(id)) {
            NodoTabla nuevo = new NodoTabla(id, valor);
            Tabla[id] = nuevo;
            Tabla[id].eliminado = false;
            numElementos++;
        } else if (existe(id) == true) {
            ListaDoble lst = getUser(id);
            if (lst.existe(valor.getCarnet()) == false) {
                lst.agregar(valor);
            } else {
                System.out.println("ya existe");
            }
        }
    }

    private void borrar(int id) {
        if (existe(id)) {
            Tabla[id] = null;
        }
    }

    public int posicionTabla(int carnet) {
        int p = carnet % TAMTABLA;
        return p;
    }

    private ListaDoble getUser(int id) {
        for (int i = 0; i < TAMTABLA; i++) {
            if (Tabla[i] != null) {
                if (Tabla[i].id == id) {
                    return Tabla[i].lst;
                }
            }
        }
        return null;
    }

    public NodoTabla Buscar(int id) {
        int valor = 0;

        for (int i = 0; i < TAMTABLA; i++) {
            if (Tabla[i] != null) {
                if (id == Tabla[i].id) {
                    valor = i;
                }
            } else {
            }
        }
        return Tabla[valor];
    }

    int getTamTabla() {
        return TAMTABLA;
    }

    public void printGraphviz() throws IOException {
        int cont = 0, cont2 = 0;
        PrintWriter sw = new PrintWriter(new FileWriter("Tabla.txt"));
        sw.println("digraph G{ bgcolor = white \n node[fontcolor = \"black \", height = 0.5, color = \"gray \"] \n [shape=box, style=filled, color=gray] \n rankdir=UD \n edge  [color=\"black\", dir=fordware]");
        for (int i = 0; i < TAMTABLA; i++) {
            sw.print(i + "[style =\"filled\"; label=\"" + i + "\";pos= \"1,-" + cont + "! \"]\n");
            cont = cont + 2;
        }
        cont = 0;
        for (int i = 0; i < TAMTABLA; i++) {
            if (Tabla[i] != null) {
                if (cont == 0) {
                    sw.print(Tabla[i].id);
                } else {
                    sw.print(" -> " + Tabla[i].id);
                }
                cont++;
            } else {
                if (cont == 0) {
                    sw.print(i);
                } else {
                    sw.print(" -> " + i);
                }
                cont++;
            }
            cont2 = i;
        }
        sw.println(";");
        cont = 0;
        int cont3 = 5;
        if (cont2 == TAMTABLA - 1) {
            for (int i = 0; i < TAMTABLA; i++) {
                if (Tabla[i] != null) {
                    ListaDoble lst = Tabla[i].lst;
                    if (lst.primero != null) {
                        NodoLista aux = lst.primero;
                        while (aux != null) {
                            sw.println(aux.getDato().getCarnet() + "[pos= \"" + cont3 + ",-" + cont + "! \"]\n");
                            cont3 = cont3 + 7;

                            aux = aux.siguiente;
                        }
                        cont3 = 5;
                    }
                    if (lst.primero != null) {
                        sw.println(Tabla[i].id + " -> " + lst.primero.getDato().getCarnet() + ";");
                        sw.println(lst.graficarLista());
                    }

                }
                cont = cont + 2;
            }
        }
        sw.println("\t } \n");
        sw.close();
        String dotPath = "fdp";
        String fileInputPath = "Tabla.txt";
        String fileOutputPath = "Tabla.png";
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

    public void eliminar(int carnet) {
        int id = posicionTabla(carnet);
        if (existe(id) == true) {
            ListaDoble lst = getUser(id);
            if (lst.existe(carnet) == true) {
                lst.borrar(carnet);
            } else {
                return;
            }
        }

    }

    static void ExecuteCommand(String _Command) throws IOException {
        Runtime.getRuntime().exec(_Command);
    }
}
