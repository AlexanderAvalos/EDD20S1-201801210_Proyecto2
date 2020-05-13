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
public class Nodo {

    private Nodo siguiente;
    private Dato valor;

    public Nodo() {
        this.valor = null;
        this.siguiente = null;
    }

    public Nodo(Dato valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Dato getValor() {
        return valor;
    }

    public void setValor(Dato valor) {
        this.valor = valor;
    }

}
