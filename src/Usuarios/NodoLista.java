/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

/**
 *
 * @author Alex
 */
public class NodoLista {
    private Usuario dato;
    public NodoLista siguiente,anterior;
 
    public NodoLista(Usuario dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public Usuario getDato() {
        return dato;
    }

    public void setDato(Usuario dato) {
        this.dato = dato;
    }
}
