/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import java.awt.Event;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.net.ProgressSource;

/**
 *
 * @author Alex
 */
public class ListaDoble {

    NodoLista primero, ultimo;
    int size;

    public ListaDoble() {
        this.size = 0;
        this.primero = this.ultimo = null;
    }

    void borrarLista() {
        primero = null;
        ultimo = null;
        size = 0;
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

    public void agregar(Usuario user) {
        NodoLista nuevo = new NodoLista(user);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            size++;
            return;
        } else {
            if (existe(user.getCarnet()) == false) {
                ultimo.siguiente = nuevo;
                nuevo.anterior = ultimo;
                ultimo = nuevo;
                size++;
                return;
            } else {
                System.out.println("ya existe");
                return;
            }
        }
    }

    private Usuario get(int index) {
        int fin = 0;
        Usuario result = null;
        NodoLista temporal = primero;

        while (index < fin) {
            result = temporal.getDato();
            temporal = temporal.siguiente;

        }
        return result;
    }

    public void borrar(int carnet) {
        NodoLista temporal = primero;
        while (temporal != null) {
            if (temporal.getDato().getCarnet() == carnet) {
                if (temporal == primero) {
                    primero = temporal.siguiente;
                    size--;
                    return;
                } else {
                    if (temporal.siguiente == null) {
                        temporal.anterior.siguiente = null;
                        temporal = null;
                    } else {
                        temporal.anterior.siguiente = temporal.siguiente;
                        temporal = null;
                    }
                    size--;
                    return;
                }
            } else {
                temporal = temporal.siguiente;
            }
        }
    }

    boolean existe(int carnet
    ) {
        NodoLista temporal = primero;
        while (temporal != null) {
            if (temporal.getDato().getCarnet() == carnet) {
                return true;
            }
        }
        return false;
    }

    public Usuario buscar(int carnet) {
        NodoLista temporal = primero;
        while (temporal != null) {
            if (temporal.getDato().getCarnet() == carnet) {
                return temporal.getDato();
            } else {
                temporal = temporal.siguiente;
            }
        }
        return null;
    }

    public boolean getUser(int carnet) {
        NodoLista temp = primero;
        while (temp != null) {
            if (temp.getDato().getCarnet() == carnet) {
                return true;
            } else {
                temp = temp.siguiente;
            }
        }
        return false;
    }

    public String imprimir() {
        String result = "";
        NodoLista temporal = primero;
        if (primero == null) {
            System.out.print("No exist valores en esta lista");
        } else {
            while (temporal != null) {
                Usuario auxiliar = temporal.getDato();
                //qInfo()<< auxiliar->nombre<<" ----- "<<auxiliar->unidades;
                result += "Carnet: " + auxiliar.getCarnet() + " Nombre: " + auxiliar.getNombre() + "\n";
                temporal = temporal.siguiente;
            }
        }
        return result;
    }

    public String graficarLista() {
        String result = "";
        NodoLista temp = primero;
        while (temp != null) {
            result += temp.getDato().getNombre() + " [label= \"Carnet: " + temp.getDato().getCarnet() + ", Password: " + getMD5(temp.getDato().getPass()) + "\"]; \n";
            temp = temp.siguiente;
        }
        temp = primero;
        while (temp != null) {
            if (temp == primero) {
                result += temp.getDato().getNombre();
            } else {
                result += "-> " + temp.getDato().getNombre();
            }
            temp = temp.siguiente;
        }
        return result;
    }
}
