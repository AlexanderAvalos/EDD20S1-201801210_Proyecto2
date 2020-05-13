/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Biblioteca.NodoAvl;
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
        this.Tabla = new NodoTabla[TAMTABLA]; // Reserva la cantidad de posiciones de memorias a utilizar
        for (int i = 0; i < TAMTABLA; i++) {
            Tabla[i] = null; // inicializar todas las posiciones de la tabla en null
        }
        numElementos = 0; // numero de elemntos
    }

    public int posicionTabla(int id) {
        //Aqui se implementa la funcion de dispercion
        int i = 0, p;
        p = id % TAMTABLA;
        return p;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existe(int id) {

        NodoTabla actual = Tabla[id]; // obtiene la posicion de memoria si el identificador ya fue insertado
        if (actual != null) // si actual no es vacio
        {
            if (actual.eliminado) // verifica si ya fue insertado y eliminado
            {
                return false; // si fue eliminado, retornar falso, esto es por si se quiere insertar nuevamente el identificador a la tabla
            } else {
                return true; // si no fue eliminado, entonces existe
            }
        } else {
            return false; // si la posicion de la tabla es nulo, entonces no existe elemento
        }
    }

    public void insertar(int id, Usuario valor) {
        if (!existe(id)) // verifica si el elemento ya fue insertado
        {
            NodoTabla nuevo = new NodoTabla(id, valor); // crea un nuevo nodo
            int posicion = posicionTabla(id); // busca en que posicion ponerlo
            Tabla[posicion] = nuevo;
            Tabla[posicion].eliminado = false;
            numElementos++;
        } else if (existe(id) == true) {
            ListaDoble lst = getUser(id);
            if (lst.existe(valor.getCarnet()) == false) {
                lst.agregar(valor);
            } else {
                lst.getUser(valor.getCarnet());
            }
        }
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

    private void borrar(int id) {
        if (existe(id)) //busca si existe entonces borrar
        {
            Tabla[id] = null;
        }
    }

    public String imprimir() {
        String result = null;

        for (int i = 0; i < TAMTABLA; i++) {
            if (Tabla[i] != null) {
                System.out.println(Tabla[i].id + " " + Tabla[i].lst.imprimir());
                result += "-------------" + Tabla[i].id + "-------------";
                result += Tabla[i].lst.imprimir();

            } else {
                result += "-------------NULL-------------\n";
            }
        }
        return result;
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
        // sw.print(Tabla[i].id % TAMTABLA + "[style =\"filled\"; label=\"" +Tabla[i].id % TAMTABLA  + "\"; pos= \""+ cont +",0! \"]\n");
        for (int i = 0; i < TAMTABLA; i++) {
            sw.print(i + "[style =\"filled\"; label=\"" + i + "\";pos= \"1,-" + cont + "! \"]\n");
            cont++;
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
        if (cont2 == TAMTABLA - 1) {
            for (int i = 0; i < TAMTABLA; i++) {
                if (Tabla[i] != null) {
                    ListaDoble lst = Tabla[i].lst;
//                     pos= \""+ cont +",0! \"
                    if (lst.primero != null) {
                        sw.println(lst.primero.getDato().getNombre() + "[pos= \"5,-" + cont + "! \"]\n");
                    }
                    if (lst.primero != null) {
                        sw.println(Tabla[i].id + " -> " + lst.primero.getDato().getNombre() + ";");
                        sw.println(lst.graficarLista());
                    }

                }
                cont++;
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

    public void eliminar(int id, int carnet) {
        if (existe(id) == true) {
            ListaDoble lst = getUser(id);
            if (lst.existe(carnet) == true) {
                lst.borrar(carnet);
            } else {
                return;
            }

            if (lst.primero == null) {
                borrar(id);
            }
        }

    }

    static void ExecuteCommand(String _Command) throws IOException {
        //Indicamos que deseamos inicializar el proceso cmd.exe junto a un comando de arranque. 
        //(/C, le indicamos al proceso cmd que deseamos que cuando termine la tarea asignada se cierre el proceso).
        //Para mas informacion consulte la ayuda de la consola con cmd.exe /? 
        Runtime.getRuntime().exec(_Command);
// Indicamos que la salida del proceso se redireccione en un Stream
    }
}
