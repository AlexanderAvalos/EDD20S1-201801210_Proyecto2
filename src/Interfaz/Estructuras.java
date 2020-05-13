/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Biblioteca.ArbolB;
import Biblioteca.AVL;
import Usuarios.TablaHash;
import Block.ListaSimple;
import Usuarios.ListaDoble;

/**
 *
 * @author Alex
 */
public class Estructuras {

    private static ArbolB arbolB;
    private static AVL arbolAVL;
    private static TablaHash tablahash;
    private static ListaSimple lista;
    private static ListaDoble listaDoble;

    public Estructuras() {
        this.arbolB = new ArbolB();
        this.arbolAVL = new AVL();
        this.tablahash = new TablaHash();
        this.lista = new ListaSimple();
        this.listaDoble = new ListaDoble();
    }

    public static ListaDoble getListaDoble() {
        return listaDoble;
    }

    public static void setListaDoble(ListaDoble listaDoble) {
        Estructuras.listaDoble = listaDoble;
    }

    public static ArbolB getArbolB() {
        return arbolB;
    }

    public static AVL getArbolAVL() {
        return arbolAVL;
    }

    public static TablaHash getTablahash() {
        return tablahash;
    }

    public static ListaSimple getLista() {
        return lista;
    }
}
