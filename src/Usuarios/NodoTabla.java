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
public class NodoTabla {

    public int id;
    public ListaDoble lst;
    public boolean eliminado;

    public NodoTabla(int id, Usuario valor) {
        this.id = id;
        this.lst = new ListaDoble();
        lst.agregar(valor);
    }
}
