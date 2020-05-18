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
public class NodoArbolB {
    public static int m = 5;
    private static int numPunteros = m + 1;
    private static int numLlaves = m;
    public Boolean hoja = true;
    public int ULlave = 1;
    public Libro[] llaves = new Libro[numLlaves];
    public NodoArbolB[] punteros = new NodoArbolB[numPunteros];
    public NodoArbolB Padre;

    public NodoArbolB(Object objeto) {
        Libro donacion = (Libro) objeto;
        llaves[0] = donacion;

    }

    public NodoArbolB() {
        for (int i = 0; i < m - 1; i++) {
            llaves[i] = null;
        }
        this.Padre = null;
    }
    
}
