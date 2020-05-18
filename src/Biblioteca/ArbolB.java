/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Alex
 */
public class ArbolB {
    private JTextArea area = new JTextArea();
    private ArbolB nodoauxiliar = null;
    ArrayList<Libro> lstnodos = new ArrayList<>();
    ArrayList<Libro> lst = new ArrayList<>();
    public NodoArbolB raiz, aux;
    private static ArbolB instancia = null;
    private String valor;

    public static ArbolB getInstancia() {
        if (instancia == null) {
            instancia = new ArbolB();
        }
        return instancia;
    }

    public int compare(int s1, int s2) {
        if (s1 > s2) {
            return -1;
        } else if (s1 < s2) {
            return 1;
        } else if (s1 == s2) {
            return 0;
        } else {
            return s1 = s2;
        }
    }

    public int comparer(int s1, int s2) {
        if (s1 > s2) {
            return 1;
        } else if (s1 < s2) {
            return -1;
        } else if (s1 == s2) {
            return 0;
        } else {
            return s1 = s2;
        }
    }

    private NodoArbolB buscar(Libro libro, NodoArbolB node) {
        if (node != null) {
            int i = 1;
            while (i <= node.ULlave && (comparer(node.llaves[i - 1].getIsbn(), libro.getIsbn()) < 0)) {
                i++;
            }
            if (i > node.ULlave || (comparer(node.llaves[i - 1].getIsbn(), libro.getIsbn()) > 0)) {
                return buscar(libro, node.punteros[i - 1]);
            } else {
                return node;
            }
        } else {
            return null;
        }
    }

    public Libro exi(int libro) {
        Libro aux = recorrer(libro);
        if (aux == null) {
            System.out.println("no existe");
            return null;
        } else {
            return aux;
        }
    }

    public JTextArea exis(String texto) {
        recorrerA(this.raiz, texto);
        valor = "";
        return area;
    }

    private void recorrerA(NodoArbolB nodo, String texto) {
        if (nodo != null) {
            for (int i = 0; i < 4; i++) {
                if (nodo.llaves[i] != null) {
                    if (nodo.llaves[i].getTitulo().contains(texto) || nodo.llaves[i].getTitulo().equals(texto)) {
                        valor += " " + nodo.llaves[i].getTitulo() + "  " + nodo.llaves[i].getIsbn() + '\n';
                        System.out.println();
                        area.append(String.valueOf(nodo.llaves[i].getIsbn())+" "+ nodo.llaves[i].getTitulo()+'\n');
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                if (nodo.punteros[i] != null) {
                    ordenar(nodo.punteros[i]);
                }
            }
        }
    }

    private Libro recorrer(int libro) {
        NodoArbolB aux = buscaris(libro, this.raiz);
        if (aux != null) {
            for (int i = 0; i < aux.llaves.length; i++) {
                if (aux.llaves[i] != null) {
                    if (aux.llaves[i].getIsbn() == libro) {
                        return aux.llaves[i];
                    }
                }
            }

        }
        return null;
    }

    private NodoArbolB buscaris(int libro, NodoArbolB node) {
        if (node != null) {
            int i = 1;
            while (i <= node.ULlave && (comparer(node.llaves[i - 1].getIsbn(), libro) < 0)) {
                i++;
            }
            if (i > node.ULlave || (comparer(node.llaves[i - 1].getIsbn(), libro) > 0)) {
                return buscaris(libro, node.punteros[i - 1]);
            } else {
                return node;
            }
        } else {
            return null;
        }
    }

    public Boolean existe(Libro libro) {
        if (buscar(libro, raiz) == null) {
            return false;
        } else {
            return true;
        }
    }

    public void insert(Libro temporal) {
        if (existe(temporal) == true) {
            System.out.println("ya existe");
        } else {
            insertar(temporal);
            ordenar(this.raiz);
        }
    }

    private void ordenar(NodoArbolB nodo) {
        if (nodo != null) {
            for (int i = 0; i < (nodo.llaves.length - 1); i++) {
                if (nodo.llaves[i] != null) {
                    for (int j = i + 1; j < nodo.llaves.length; j++) {
                        if (nodo.llaves[j] != null) {
                            int val = nodo.llaves[i].getIsbn();
                            int val2 = nodo.llaves[j].getIsbn();
                            if (val > val2) {
                                Libro aux = nodo.llaves[i];
                                nodo.llaves[i] = nodo.llaves[j];
                                nodo.llaves[j] = aux;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                if (nodo.punteros[i] != null) {
                    ordenar(nodo.punteros[i]);
                }
            }

        }
    }

    private void insertar(Libro libro) {
        if (this.raiz == null) {
            this.raiz = new NodoArbolB(libro);
            return;
        }
        NodoArbolB nodo = raiz;

        while (nodo.hoja == false) {
            int i = 1;
            while (i <= nodo.ULlave && nodo.llaves[i - 1].getIsbn() != libro.getIsbn()) {
                i++;
            }
            nodo = nodo.punteros[i - 1];
        }

        Boolean posInLeaf = true;
        NodoArbolB node1 = null;
        NodoArbolB node2 = null;

        while (true) {
            int i = 1;
            while (i <= nodo.ULlave && nodo.llaves[i - 1].getIsbn() != libro.getIsbn()) {
                i++;
            }

            if (i <= nodo.ULlave) {
                int finalPointer = nodo.ULlave - 1;
                while (finalPointer >= i - 1) {
                    nodo.llaves[finalPointer + 1] = nodo.llaves[finalPointer];

                    nodo.punteros[finalPointer + 2] = nodo.punteros[finalPointer + 1];
                    finalPointer--;
                }
            }

            nodo.llaves[i - 1] = libro;

            nodo.ULlave = nodo.ULlave + 1;

            if (node1 != null && node2 != null) {
                nodo.punteros[i - 1] = node1;
                nodo.punteros[i] = node2;

                node1 = null;
                node2 = null;
            }

            int order = 6;

            if (nodo.ULlave < order - 1) {
                return;
            } else {
                int posInMiddleKey = (order / 2) - 1;

                libro = nodo.llaves[posInMiddleKey];

                node1 = nodo;
                node2 = new NodoArbolB();

                if (posInLeaf) {
                    node1.hoja = true;
                    node2.hoja = true;
                } else {
                    node1.hoja = false;
                    node2.hoja = false;
                }

                node1.llaves[posInMiddleKey] = null;

                node1.ULlave = (order / 2) - 1;
                node2.ULlave = (order / 2) - 1;
                int posInNode1 = posInMiddleKey + 1;
                int posInNode2 = 0;
                while (posInNode1 < order - 1) {

                    node2.llaves[posInNode2] = node1.llaves[posInNode1];

                    node1.llaves[posInNode1] = null;

                    node2.punteros[posInNode2] = node1.punteros[posInNode1];
                    if (node2.punteros[posInNode2] != null) {
                        node2.punteros[posInNode2].Padre = node2;
                    }

                    node1.punteros[posInNode1] = null;

                    posInNode1++;
                    posInNode2++;

                    if (posInNode1 == order - 1) {
                        node2.punteros[posInNode2] = node1.punteros[posInNode1];
                        if (node2.punteros[posInNode2] != null) {
                            node2.punteros[posInNode2].Padre = node2;
                        }

                        node1.punteros[posInNode1] = null;
                    }

                }

                if (nodo == this.raiz) {

                    NodoArbolB auxRaiz = new NodoArbolB(libro);
                    auxRaiz.hoja = false;
                    auxRaiz.punteros[0] = node1;
                    auxRaiz.punteros[1] = node2;

                    node1.Padre = auxRaiz;
                    node2.Padre = auxRaiz;

                    this.raiz = auxRaiz;
                    return;

                } else {
                    nodo = nodo.Padre;
                    node2.Padre = node1.Padre;
                    posInLeaf = false;
                }

            }
        }
    }

    public void Eliminar(int isbn) {
        nodoauxiliar = new ArbolB();
        eliminar(this.raiz, isbn);
        this.raiz = nodoauxiliar.raiz;
        nodoauxiliar = null;
    }

    private void eliminar(NodoArbolB nodo, int valor) {
        if (nodo != null) {
            for (int i = 0; i < 4; i++) {
                if (nodo.llaves[i] != null) {
                    if (nodo.llaves[i].getIsbn() != valor) {
                        Libro nuevo = new Libro(nodo.llaves[i].getIsbn(), nodo.llaves[i].getTitulo(), nodo.llaves[i].getAutor(), nodo.llaves[i].getEditorial(), nodo.llaves[i].getAño(),
                                nodo.llaves[i].getEdicion(), nodo.llaves[i].getCategoria(), nodo.llaves[i].getIdioma(), nodo.llaves[i].getCarnet());
                        nodoauxiliar.insert(nuevo);
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                if (nodo.punteros[i] != null) {
                    eliminar(nodo.punteros[i], valor);
                }
            }
        }
    }

    public void printGraphviz() throws IOException {
        NodoArbolB auxNode = this.raiz;

        PrintWriter sw = new PrintWriter(new FileWriter("ArbolB.txt"));
        sw.println("digraph G {");
        sw.println("\t rankdir = TB; \n");
        sw.println("\t node[shape=record]; \n");
        sw.println("\t subgraph clusterBTree { \n");
        sw.println("\t node [shape=record];\nnode [style=filled];\nnode [fillcolor=\"#EEEEEE\"];\nnode [color=\"#8C8C8E\"];\nedge [color=\"#31CEF0\"]; \n");
        print(auxNode, sw);
        sw.println("\t } \n");
        sw.println("\t } \n");
        sw.close();
        String dotPath = "dot";
        String fileInputPath = "ArbolB.txt";
        String fileOutputPath = "ArbolB.png";
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

    private void print(NodoArbolB nodo, PrintWriter sw) {
        if (nodo != null) {
            if (nodo.Padre != null) {
                sw.println("\"" + "ISBN: " + nodo.Padre.llaves[0].getIsbn() + " Titulo: " + nodo.Padre.llaves[0].getTitulo() + "\" -> \"" + "ISBN: " + nodo.llaves[0].getIsbn() + " Titulo: " + nodo.llaves[0].getTitulo() + "\"");
            }
            sw.println("\n\"" + "ISBN: " + nodo.llaves[0].getIsbn() + " Titulo: " + nodo.llaves[0].getTitulo() + "\"[label=\"|");
            for (int i = 0; i < 4; i++) {
                if (i != 0) {
                    sw.println("|");
                }
                if (nodo.llaves[i] != null) {
                    sw.println(" ISBN: " + nodo.llaves[i].getIsbn() + " Titulo: " + nodo.llaves[i].getTitulo());
                } else {
                    sw.println(0);
                }

            }
            sw.println("\"];\n");
            for (int i = 0; i < 5; i++) {
                if (nodo.punteros[i] != null) {
                    print(nodo.punteros[i], sw);
                }
            }
        }
    }

    static void ExecuteCommand(String _Command) throws IOException {

        Runtime.getRuntime().exec(_Command);

    }

    public void set(Libro valor, Libro Reemplazo) {
        if (this.existe(valor)) {
            NodoArbolB nodo = this.buscar(valor, this.raiz);
            this.ReemplazarLlave(nodo, valor, Reemplazo);
        }
    }

    public NodoArbolB PrecedenciaNodo(Libro valor, NodoArbolB nodo) {
        int i = 1;
        while (i <= nodo.ULlave) {
            if (nodo.llaves[i - 1].equals(valor)) {
                nodo = nodo.punteros[i];
                break;
            }
            i++;
        }
        while (true) {
            if (nodo.hoja) {
                return nodo;
            } else {
                nodo = nodo.punteros[0];
            }
        }
    }

    public void ReemplazarLlave(NodoArbolB nodo, Libro valor, Libro valor2) {
        int i = 1;
        while (i <= nodo.ULlave) {
            if (nodo.llaves[i - 1].equals(valor)) {
                nodo.llaves[i - 1] = valor2;
                break;
            }
            i++;
        }
    }

    public void EliminarHoja(NodoArbolB nodo, Libro valor) {
        int i = 0;
        while (i < nodo.ULlave) {
            if (nodo.llaves[i].equals(valor)) {
                while (i < nodo.ULlave - 1) {
                    nodo.llaves[i] = nodo.llaves[i + 1];

                    i++;
                }
                nodo.llaves[nodo.ULlave - 1] = null;

                nodo.ULlave--;
            } else {
                i++;
            }
        }
    }

    private Boolean VerificarMinimo(NodoArbolB nodo) {
        if (nodo == this.raiz) {
            if (nodo.ULlave == 0) {
                this.raiz = null;
            }
            return true;
        }
        int minKeys = 2;
        if (nodo.ULlave >= minKeys) {
            return true;
        } else {
            return false;
        }
    }

    private NodoArbolB izquierda(NodoArbolB nodo) {
        int i = 0;
        NodoArbolB Padre = nodo.Padre;
        while (i <= Padre.ULlave) {
            if (Padre.punteros[i] == nodo) {
                if (i == 0) {
                    return null;
                } else {
                    return Padre.punteros[i - 1];
                }
            }
            i++;
        }
        return null;
    }

    private NodoArbolB derecha(NodoArbolB nodo) {
        int i = 0;
        NodoArbolB Padre = nodo.Padre;
        while (i <= Padre.ULlave) {
            if (Padre.punteros[i] == nodo) {
                if (i == Padre.ULlave) {
                    return null;
                } else {
                    return Padre.punteros[i + 1];
                }
            }
            i++;
        }
        return null;
    }

    private void TomardeDerecha(NodoArbolB nodo) {
        NodoArbolB Padre = nodo.Padre;
        int i = 0;
        while (i < Padre.ULlave) {
            if (Padre.punteros[i] == nodo) {
                NodoArbolB derecha = Padre.punteros[i + 1];
                nodo.llaves[nodo.ULlave] = Padre.llaves[i];

                nodo.ULlave++;
                nodo.punteros[nodo.ULlave] = derecha.punteros[0];
                Padre.llaves[i] = derecha.llaves[0];

                int k = 0;
                while (k < derecha.ULlave - 1) {
                    derecha.llaves[k] = derecha.llaves[k + 1];

                    derecha.punteros[k] = derecha.punteros[k + 1];
                    k++;
                }
                derecha.llaves[k] = null;

                derecha.punteros[k] = derecha.punteros[k + 1];
                derecha.ULlave--;
            }
            i++;
        }
    }

    private void TomardeIzquierda(NodoArbolB nodo) {
        NodoArbolB Padre = nodo.Padre;
        int i = 0;
        while (i <= Padre.ULlave) {
            if (Padre.punteros[i] == nodo) {
                NodoArbolB izquierda = Padre.punteros[i - 1];
                int k = 0;
                while (k < nodo.ULlave) {
                    nodo.llaves[k + 1] = nodo.llaves[k];

                    nodo.punteros[k + 1] = nodo.punteros[k];
                    k++;
                }
                nodo.punteros[k + 1] = nodo.punteros[k];
                nodo.llaves[0] = Padre.llaves[i - 1];

                nodo.punteros[0] = izquierda.punteros[izquierda.ULlave];
                Padre.llaves[i - 1] = izquierda.llaves[izquierda.ULlave - 1];

                izquierda.llaves[izquierda.ULlave - 1] = null;

                izquierda.punteros[izquierda.ULlave] = null;
                izquierda.ULlave--;
                nodo.ULlave++;
                break;
            }
            i++;
        }
    }

    private void NuevaRaiz(NodoArbolB left, NodoArbolB right) {
        left.llaves[left.ULlave] = raiz.llaves[0];

        left.ULlave++;
        int leftPointer = left.ULlave;
        int i = 0;
        while (i < right.ULlave) {
            left.llaves[leftPointer] = right.llaves[i];

            if (right.punteros[i] != null) {
                left.punteros[leftPointer] = right.punteros[i];
                right.punteros[i].Padre = left;
            }
            i++;
            leftPointer++;
            left.ULlave++;
        }
        if (right.punteros[i] != null) {
            left.punteros[leftPointer] = right.punteros[i];
            right.punteros[i].Padre = left;
        }
        raiz = left;
        raiz.Padre = null;
    }

    private void combinar(NodoArbolB nodo) {
        if (derecha(nodo) != null) {
            NodoArbolB Padre = nodo.Padre;
            int i = 0;
            while (i < Padre.ULlave) {
                if (Padre.punteros[i] == nodo) {
                    nodo.llaves[nodo.ULlave] = Padre.llaves[i];
                    nodo.ULlave++;
                    NodoArbolB partner = derecha(nodo);
                    int k = 0;
                    while (k < partner.ULlave) {
                        nodo.llaves[nodo.ULlave] = partner.llaves[k];
                        nodo.punteros[nodo.ULlave] = partner.punteros[k];
                        nodo.ULlave++;
                        k++;
                    }
                    nodo.punteros[nodo.ULlave] = partner.punteros[k];
                    int pointer = i;
                    while (pointer < Padre.ULlave) {
                        Padre.llaves[pointer] = Padre.llaves[pointer + 1];
                        Padre.punteros[pointer + 1] = Padre.punteros[pointer + 2];
                        pointer++;
                    }
                    Padre.ULlave--;
                    break;
                }
                i++;
            }
        } else {
            combinar(izquierda(nodo));
        }
    }

    public void Remover(int valor) {

        for (int i = 0; i < lstnodos.size(); i++) {

            NodoArbolB nodo = buscar(lstnodos.get(i), this.raiz);

            if (nodo != null) {

                if (nodo.hoja == false) {
                    NodoArbolB hoja = PrecedenciaNodo(lstnodos.get(i), nodo);
                    Libro presedencia = hoja.llaves[0];
                    ReemplazarLlave(nodo, lstnodos.get(i), presedencia);
                    nodo = hoja;
                    EliminarHoja(hoja, presedencia);
                } else {

                    EliminarHoja(nodo, lstnodos.get(i));
                }

                while (true) {
                    if (VerificarMinimo(nodo)) {
                        break;
                    } else if (derecha(nodo) != null && derecha(nodo).ULlave > 2) {
                        TomardeDerecha(nodo);
                        break;
                    } else if (izquierda(nodo) != null && izquierda(nodo).ULlave > 2) {
                        TomardeIzquierda(nodo);
                        break;
                    } else if (nodo.Padre == this.raiz) {
                        if (nodo.Padre.ULlave == 1) {
                            if (derecha(nodo) != null) {
                                NuevaRaiz(nodo, derecha(nodo));
                            } else if (izquierda(nodo) != null) {
                                NuevaRaiz(izquierda(nodo), nodo);
                            }
                        } else {
                            combinar(nodo);
                        }
                        break;
                    } else {
                        combinar(nodo);
                        nodo = nodo.Padre;

                    }
                }
            }
        }
    }

}
