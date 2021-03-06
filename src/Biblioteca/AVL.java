/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JComboBox;
import Interfaz.Biblioteca;
import javax.swing.JTextArea;

/**
 *
 * @author Alex
 */
public class AVL {

    private JComboBox catalogo = new JComboBox();
    private JTextArea titulo = new JTextArea();
    private NodoAvl raiz = null;
    private boolean derecha, izquierda;
    private boolean match = false;
    private String titulos;
    private NodoAvl busqueda = null;
    private NodoAvl au = null;
    private NodoAvl resultadobusqueda = null;

    public JComboBox valor() {
        catalogo.removeAllItems();
        post(this.raiz);
        return catalogo;
    }

    public void insertar(String valor, int carnet) {
        raiz = insert(valor, raiz, carnet);
        enlazar(raiz, null);
    }

    public void impr() {
        imprimir(this.raiz, null);
    }

    private void imprimir(NodoAvl nodo, NodoAvl padre) {
        if (nodo != null) {
            if (padre == null) {
                System.out.println(nodo.getValor());
                imprimir(nodo.getIzquierda(), nodo);
                imprimir(nodo.getDerecha(), nodo);
            } else {
                System.out.println(padre.getValor() + " -> " + nodo.getValor());
                imprimir(nodo.getIzquierda(), nodo);
                imprimir(nodo.getDerecha(), nodo);
            }
        }
    }

    private void post(NodoAvl nodo) {
        if (nodo == null) {
            return;
        }
        catalogo.addItem(nodo.getValor());
        post(nodo.getIzquierda());
        post(nodo.getDerecha());

    }

    public NodoAvl serch(int libro) {
        recorrer(this.raiz, libro);
        return busqueda;
    }

    private void recorrer(NodoAvl nodo, int libro) {
        if (nodo == null) {
            return;
        }
        Libro aux = nodo.getArbol().exi(libro);
        if (aux != null) {
            if (aux.getIsbn() == libro) {
                busqueda = nodo;
            }
        }
        recorrer(nodo.getIzquierda(), libro);
        recorrer(nodo.getDerecha(), libro);
    }

    public JTextArea buscar(String texto) {
        recorrer1(this.raiz, texto);
        return titulo;
    }

    private void recorrer1(NodoAvl nodo, String texto) {
        if (nodo == null) {
            return;
        }
        titulo.append(nodo.getArbol().exis(texto).getText());
        recorrer1(nodo.getIzquierda(), texto);
        recorrer1(nodo.getDerecha(), texto);
    }

    private int comparar(String v1, String v2) {
        int resultado = 0;
        if (v1.compareTo(v2) < 0) {
            resultado = -1;
        } else if (v1.compareTo(v2) > 0) {
            resultado = 1;
        } else {
            resultado = 0;
        }
        return resultado;
    }

    private NodoAvl insert(String valor, NodoAvl raiz, int carnet) {
        if (raiz == null) {
            raiz = new NodoAvl();
            raiz.setValor(valor);
            raiz.setCreador(carnet);
        } else if (comparar(valor, raiz.getValor()) < 0) {
            NodoAvl aux = insert(valor, raiz.getIzquierda(), carnet);
            raiz.setIzquierda(aux);

            if ((altura(raiz.getDerecha()) - altura(raiz.getIzquierda())) == -2) {
                if (comparar(valor, raiz.getIzquierda().getValor()) < 0) {
                    raiz = rotacionIzquierda(raiz);
                } else {
                    raiz = rotacionDobleIzquierda(raiz);
                }
            }
        } else if (comparar(valor, raiz.getValor()) > 0) {
            NodoAvl aux1 = insert(valor, raiz.getDerecha(), carnet);
            raiz.setDerecha(aux1);
            if ((altura(raiz.getDerecha()) - altura(raiz.getIzquierda())) == 2) {
                if (comparar(valor, raiz.getDerecha().getValor()) > 0) {
                    raiz = rotacionDerecha(raiz);
                } else {
                    raiz = rotacionDobleDerecha(raiz);
                }
            }

        }
        raiz.setAltura(MAX(altura(raiz.getIzquierda()), altura(raiz.getDerecha())) + 1);
        return raiz;
    }

    private void enlazar(NodoAvl nodo, NodoAvl padre) {
        if (nodo != null) {
            nodo.setPadre(padre);
            if (nodo.getDerecha() != null) {
                enlazar(nodo.getDerecha(), nodo);
            }
            if (nodo.getIzquierda() != null) {
                enlazar(nodo.getIzquierda(), nodo);
            }
        }
    }

    private int altura(NodoAvl nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.getAltura();
        }

    }

    private int MAX(int v1, int v2) {
        if (v1 > v2) {
            return v1;
        } else {
            return v2;
        }
    }

    private NodoAvl rotacionIzquierda(NodoAvl nodo) {
        NodoAvl aux = nodo.getIzquierda();
        nodo.setIzquierda(aux.getDerecha());
        aux.setDerecha(nodo);
        nodo.setAltura(MAX(altura(nodo.getIzquierda()), altura(nodo.getDerecha())) + 1);
        aux.setAltura(MAX(altura(aux.getIzquierda()), nodo.getAltura()) + 1);
        return aux;
    }

    private NodoAvl rotacionDerecha(NodoAvl nodo) {
        NodoAvl aux = nodo.getDerecha();
        nodo.setDerecha(aux.getIzquierda());
        aux.setIzquierda(nodo);
        nodo.setAltura(MAX(altura(nodo.getDerecha()), altura(nodo.getIzquierda())) + 1);
        aux.setAltura(MAX(altura(aux.getDerecha()), nodo.getAltura()) + 1);
        return aux;
    }

    private NodoAvl rotacionDobleIzquierda(NodoAvl nodo) {
        nodo.setIzquierda(rotacionDerecha(nodo.getIzquierda()));
        return rotacionIzquierda(nodo);
    }

    private NodoAvl rotacionDobleDerecha(NodoAvl nodo) {
        nodo.setDerecha(rotacionIzquierda(nodo.getDerecha()));
        return rotacionDerecha(nodo);
    }

    public void eliminar(String valor) {
        busqueda(raiz, valor);
        match = false;
        enlazar(raiz, null);
        if (resultadobusqueda != null) {
            delete(resultadobusqueda);
        } else {
            System.out.println("no se encontro");
        }
    }

    private void delete(NodoAvl nodo) {
        if (nodo.getDerecha() != null) {
            derecha = true;
        } else {
            derecha = false;
        }
        if (nodo.getIzquierda() != null) {
            izquierda = true;
        } else {
            izquierda = false;
        }

        if (derecha == false && izquierda == false) {
            reBalanceo(caso1(nodo));
        }
        if (derecha != false && izquierda == false) {
            reBalanceo(caso2(nodo));
        }
        if (derecha == false && izquierda != false) {
            reBalanceo(caso2(nodo));
        }
        if (derecha != false && izquierda != false) {
            reBalanceo(caso3(nodo));
        }
    }

    private NodoAvl caso1(NodoAvl nodo) {
        NodoAvl nodopapa = nodo.getPadre();
        if (nodo.getPadre() == null) {
            raiz = null;
            return null;
        } else {
            NodoAvl izquierdo = nodopapa.getIzquierda();
            NodoAvl derecho = nodopapa.getDerecha();
            if (izquierdo != null) {
                if (izquierdo.getValor() == nodo.getValor()) {
                    nodo.getPadre().setIzquierda(null);
                }
                if (derecho != null) {
                    if (derecho.getValor() == nodo.getValor()) {
                        nodo.getPadre().setDerecha(null);
                    }
                }
                colocarAlturas(raiz);
            }
        }
        return nodopapa;
    }

    private NodoAvl caso2(NodoAvl nodo) {
        if (nodo.getPadre() != null) {
            NodoAvl hijoizquierdo = nodo.getIzquierda();
            NodoAvl hijoderecho = nodo.getDerecha();
            NodoAvl actual;
            if (nodo.getIzquierda() != null) {
                actual = nodo.getIzquierda();
            } else {
                actual = nodo.getDerecha();
            }

            if (nodo.getPadre().getIzquierda() == nodo) {
                nodo.getPadre().setIzquierda(actual);
                actual.setPadre(nodo.getPadre());
                colocarAlturas(raiz);
                return actual.getPadre();
            }
            if (nodo.getPadre().getDerecha() == nodo) {
                nodo.getPadre().setDerecha(actual);
                actual.setPadre(nodo.getPadre());
                colocarAlturas(raiz);
                return actual.getPadre();
            }
        } else {

            NodoAvl der = nodo.getDerecha();
            NodoAvl iz = nodo.getIzquierda();

            if (der != null) {
                raiz = der;
                der.setPadre(null);
                return der;
            } else if (iz != null) {
                raiz = iz;
                iz.setPadre(null);
                return iz;
            }
            colocarAlturas(raiz);
        }
        colocarAlturas(raiz);
        return null;
    }

    private NodoAvl caso3(NodoAvl nodo) {
        NodoAvl ultimoizquierda = recorrerIzquierda(nodo.getDerecha());

        if (nodo.getPadre() == null) {
        } else if (ultimoizquierda == nodo.getDerecha()) {
            ultimoizquierda = null;
        }

        if (ultimoizquierda != null) {
            if (nodo.getPadre() == null) {
                ultimoizquierda.getPadre().setIzquierda(null);
                ultimoizquierda.setDerecha(raiz.getDerecha());
                ultimoizquierda.setIzquierda(raiz.getIzquierda());
                ultimoizquierda.setPadre(null);
                raiz = ultimoizquierda;
                return ultimoizquierda;
            } else {
                if (nodo.getPadre().getDerecha() == nodo) {
                    nodo.getPadre().setDerecha(ultimoizquierda);
                } else if (nodo.getPadre().getIzquierda() == nodo) {
                    nodo.getPadre().setIzquierda(ultimoizquierda);
                }
            }
            ultimoizquierda.getPadre().setIzquierda(null);
            ultimoizquierda.setIzquierda(nodo.getIzquierda());
            ultimoizquierda.setDerecha(nodo.getDerecha());
            ultimoizquierda.setPadre(nodo.getPadre());

            nodo.getIzquierda().setPadre(ultimoizquierda);
            nodo.getDerecha().setPadre(ultimoizquierda);

            colocarAlturas(raiz);
            return nodo.getPadre();
        } else {
            NodoAvl nodoDerecho = nodo.getDerecha();

            if (nodo.getPadre() == null) {
                raiz = nodoDerecho;
            } else {
                if (nodo.getPadre().getDerecha() == nodo) {
                    nodo.getPadre().setDerecha(nodoDerecho);
                } else if (nodo.getPadre().getIzquierda() == nodo) {
                    nodo.getPadre().setIzquierda(nodoDerecho);
                }
            }
            nodoDerecho.setPadre(nodo.getPadre());
            nodoDerecho.setIzquierda(nodo.getIzquierda());
            colocarAlturas(raiz);
            return nodoDerecho;
        }
    }

    private NodoAvl recorrerIzquierda(NodoAvl nodo) {
        if (nodo.getIzquierda() != null) {
            return recorrerIzquierda(nodo.getIzquierda());
        }
        return nodo;
    }

    public NodoAvl buscarC(String valor) {
        NodoAvl busqueda = busc(valor, raiz);
        return busqueda;
    }

    public NodoAvl busc(String valor, NodoAvl nodo) {
        if (valor.compareTo(nodo.getValor()) == 0) {
            return nodo;
        } else {
            if (valor.compareTo(nodo.getValor()) < 0) {
                return busc(valor, nodo.getIzquierda());
            } else if (valor.compareTo(nodo.getValor()) > 0) {
                return busc(valor, nodo.getDerecha());
            }
        }
        return null;
    }

    private void busqueda(NodoAvl nodo, String valor) {
        if (!match) {
            if (nodo != null) {
                if (nodo.getValor().equals(valor)) {
                    resultadobusqueda = nodo;
                    match = true;
                    return;
                }
                busqueda(nodo.getIzquierda(), valor);
                busqueda(nodo.getDerecha(), valor);
            }
        }
    }

    private void colocarAlturas(NodoAvl nodo) {
        if (nodo != null) {
            nodo.obtenerAltura();
        }
    }

    private void reBalanceo(NodoAvl nodo) {
        while (nodo != null) {
            if (estaDesbalanceado(nodo)) {
                NodoAvl x = nodo;
                NodoAvl y = mayorHijo(nodo);
                NodoAvl z = mayorHijo(y);

                nodo = reestructuracion(x, y, z);
                colocarAlturas(raiz);
            }
            if (nodo != null) {
                nodo = nodo.getPadre();
            }
        }
    }

    private boolean estaDesbalanceado(NodoAvl nodo) {
        return nodo.getFE() > 1 || nodo.getFE() < -1;
    }

    private NodoAvl mayorHijo(NodoAvl nodo) {
        NodoAvl iz = null, der = null;
        if (nodo != null) {
            if (nodo.getIzquierda() != null) {
                iz = nodo.getIzquierda();
            }
            if (nodo.getDerecha() != null) {
                der = nodo.getDerecha();
            }
            if (iz != null && der != null) {
                if (iz.getAltura() > der.getAltura()) {
                    return iz;
                } else if (iz.getAltura() < der.getAltura()) {
                    return der;
                } else {
                    return der;
                }
            } else if (iz != null) {
                return iz;
            } else if (der != null) {
                return der;
            } else {
                return null;
            }
        }
        return null;
    }

    private NodoAvl reestructuracion(NodoAvl x, NodoAvl y, NodoAvl z) {
        if (x != null && y != null && z != null) {
            if (x.getFE() < -1) {
                if (y.getFE() == 1) {
                    if (raiz != x) {
                        return rotacionDobleIzquierda(x);
                    } else {
                        raiz = rotacionDobleIzquierda(x);
                        return raiz;
                    }
                } else if (y.getFE() == -1) {
                    if (raiz != x) {
                        return rotacionIzquierda(x);
                    } else {
                        raiz = rotacionIzquierda(x);
                        return raiz;
                    }
                } else if (y.getFE() == 0) {
                    if (raiz != x) {
                        return rotacionIzquierda(x);
                    } else {
                        raiz = rotacionIzquierda(x);
                        return raiz;
                    }
                }
            } else if (x.getFE() > 1) {
                if (y.getFE() == -1) {
                    if (raiz != x) {
                        return rotacionDobleDerecha(x);
                    } else {
                        raiz = rotacionDobleDerecha(x);
                        return raiz;
                    }
                } else if (y.getFE() == 1) {
                    if (raiz != x) {
                        return rotacionDerecha(x);
                    } else {
                        raiz = rotacionDerecha(x);
                        return raiz;
                    }
                } else if (y.getFE() == 0) {
                    if (raiz != x) {
                        return rotacionDerecha(x);
                    } else {
                        raiz = rotacionDerecha(x);
                        return raiz;
                    }
                }
            }
        }
        return null;
    }

    static void ExecuteCommand(String _Command) throws IOException {
        Runtime.getRuntime().exec(_Command);
    }

    public void printGraphviz() throws IOException {
        NodoAvl auxNode = this.raiz;
        PrintWriter sw = new PrintWriter(new FileWriter("AVL.txt"));
        sw.print("digraph G {");
        sw.println("rankdir = UD; \n");
        impreArbol(auxNode, null, sw);
        sw.println("\t } \n");
        sw.close();
        String dotPath = "dot";
        String fileInputPath = "AVL.txt";
        String fileOutputPath = "AVL.png";
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

    private void impreArbol(NodoAvl nodo, NodoAvl padre, PrintWriter sw) {
        if (nodo != null) {
            if (padre == null) {
                sw.println(nodo.getValor());
                impreArbol(nodo.getIzquierda(), nodo, sw);
                impreArbol(nodo.getDerecha(), nodo, sw);
            } else {
                sw.println(padre.getValor() + " -> " + nodo.getValor());
                impreArbol(nodo.getIzquierda(), nodo, sw);
                impreArbol(nodo.getDerecha(), nodo, sw);
            }
        }
    }

    public void printGraphvizPost() throws IOException {
        NodoAvl auxNode = this.raiz;
        PrintWriter sw = new PrintWriter(new FileWriter("Post.txt"));
        sw.print("digraph G {");
        sw.println("rankdir = LR; \n");
        imppost(auxNode, sw);
        sw.println("\t } \n");
        sw.close();
        String dotPath = "dot";
        String fileInputPath = "Post.txt";
        String fileOutputPath = "Post.png";
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

    public void printGraphvizPre() throws IOException {
        NodoAvl auxNode = this.raiz;
        PrintWriter sw = new PrintWriter(new FileWriter("PRE.txt"));
        sw.print("digraph G {");
        sw.println("rankdir = LR; \n");
        imppre(auxNode, sw);
        sw.println("\t } \n");
        sw.close();
        String dotPath = "dot";
        String fileInputPath = "PRE.txt";
        String fileOutputPath = "PRE.png";
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

    public void printGraphvizIn() throws IOException {
        NodoAvl auxNode = this.raiz;
        PrintWriter sw = new PrintWriter(new FileWriter("In.txt"));
        sw.print("digraph G {");
        sw.println("rankdir = LR; \n");
        impin(auxNode, sw);
        sw.println("\t } \n");
        sw.close();
        String dotPath = "dot";
        String fileInputPath = "In.txt";
        String fileOutputPath = "In.png";
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

    private void imppost(NodoAvl nodo, PrintWriter sw) {
        if (nodo.getIzquierda() != null) {
            au = nodo.getIzquierda();
            imppost(nodo.getIzquierda(), sw);
        }
        if (nodo.getDerecha() != null) {
            sw.println("->");
            au = nodo.getDerecha();
            impin(nodo.getDerecha(), sw);
            sw.println("->");
        }
        sw.println(nodo.getValor());
        au = nodo;
    }

    private void impin(NodoAvl nodo, PrintWriter sw) {
        if (nodo.getIzquierda() != null) {
            impin(nodo.getIzquierda(), sw);
            sw.println("->");
            au = nodo.getIzquierda();
        }
        sw.println(nodo.getValor());
        au = nodo;
        if (nodo.getDerecha() != null) {
            sw.println("->");
            au = nodo.getDerecha();
            impin(nodo.getDerecha(), sw);
        }

    }

    private void imppre(NodoAvl nodo, PrintWriter sw) {
        sw.print(nodo.getValor());
        au = nodo;
        if (nodo.getIzquierda() != null) {
            sw.print("->");
            au = nodo.getIzquierda();
            imppre(nodo.getIzquierda(), sw);
        }
        if (nodo.getDerecha() != null) {
            sw.print("->");
            imppre(nodo.getDerecha(), sw);
            au = nodo.getDerecha();
        }
    }

}
