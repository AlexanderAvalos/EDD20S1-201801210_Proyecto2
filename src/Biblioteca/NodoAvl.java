/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

/**
 *
 * @author Alex
 */
public class NodoAvl {

    private String valor;
    private int creador; 
    private ArbolB arbol;
    private NodoAvl izquierda, derecha;
    private int altura;
    private NodoAvl padre;

    public ArbolB getArbol() {
        return arbol;
    }

    public void setArbol(ArbolB arbol) {
        this.arbol = arbol;
    }

    public NodoAvl getPadre() {
        return padre;
    }

    public void setPadre(NodoAvl padre) {
        this.padre = padre;
    }

    public NodoAvl getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAvl izquierda) {
        this.izquierda = izquierda;
    }

    public NodoAvl getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAvl derecha) {
        this.derecha = derecha;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAvl() {
        this.creador = 00;
        arbol = new ArbolB();
        this.valor = "";
        this.izquierda = null;
        this.derecha = null;
        this.padre = null;
        this.altura = 0;
    }

    public int getCreador() {
        return creador;
    }

    public void setCreador(int creador) {
        this.creador = creador;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int obtenerAltura() {
        int val1 = 0, val2 = 0;

        if (izquierda != null) {
            val1 = this.izquierda.obtenerAltura();
        }

        if (derecha != null) {
            val2 = this.derecha.obtenerAltura();
        }
        if (val1 != 0 && val2 != 0) {
            if (val1 < val2) {
                this.altura = val2 + 1;
            } else if (val1 > val2) {
                this.altura = val1 + 1;
            } else {
                this.altura = val1 + 1;
            }
        } else {
            if (val1 != 0) {
                this.altura = val1 + 1;
            } else if (val2 != 0) {
                this.altura = val2 + 1;
            } else {
                this.altura = 0;
            }
        }
        return this.altura;
    }

    int getFE() {
        int fe = 0, val1 = 0, val2 = 0;
        if (this.izquierda != null) {
            val1 = this.izquierda.getFE();
        }
        if (this.derecha != null) {
            val2 = this.derecha.getFE();
        }
        fe = val2 - val1;
        return fe;
    }
}
