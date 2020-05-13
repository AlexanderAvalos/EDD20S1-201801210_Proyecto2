/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Block;

/**
 *
 * @author Alex
 */
public class NodoDoble {
    private Bloque dato;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    public NodoDoble(Bloque dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }  
    public NodoDoble( ) {
        this.dato = null;
        this.siguiente = null;
        this.anterior = null;
    }

    public Bloque getDato() {
        return dato;
    }

    public void setDato(Bloque dato) {
        this.dato = dato;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
    
}
