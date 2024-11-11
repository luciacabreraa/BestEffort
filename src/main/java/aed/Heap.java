package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<Traslado> {
    // Vamos a usar 2 heaps para ordenar Traslados por rentabilidad y por
    // antiguedad. Como ambos van a tener un int para ordenar la prioridad
    // (ganancia y timestamp), la prioridad de los nodos se puede hacer con int.
    // El tipo generico T es para los elementos del heap.

    ArrayList<Nodo> elems;
    int longitud;
    HeapComparator comparator;

    public class Nodo {
        private Traslado valor;
        private Nodo padre;
        private Nodo izq;
        private Nodo der;

        Nodo(Traslado v) {
            valor = v;
            padre = null;
            izq = null;
            der = null;
        }
    }

    // Constructor
    public Heap(Boolean atributo) {
        elems = new ArrayList();
        longitud = 0;
        comparator = new HeapComparator(atributo);
    }

    // Devuelve el elemento de maxima prioridad
    public Traslado max() {
        return elems.get(0).valor;
    }

    // Dado un elemento, lo encola. El entero p sera el criterio que
    // usemos para encolar, o sea, ganancias, perdidas o antiguedad.
    public void encolar(Traslado valor) {        
        Nodo nuevo = new Nodo(valor);
        if (longitud == 0) {
            elems.add(nuevo);
            longitud++;
        } else {
            elems.add(nuevo);
            longitud++;
            int posicionPadre = longitud / 2 - 1;
            nuevo.padre = elems.get(posicionPadre);
            if (elems.get(posicionPadre).izq == null) {
                elems.get(posicionPadre).izq = nuevo;
            } else {
                elems.get(posicionPadre).der = nuevo;
            }
            heapifyUp(nuevo);
        }

    }

    // Quitar el elemento de maxima prioridad
    public Traslado desencolar() {
        Nodo ultimo = elems.get(longitud - 1);
        Nodo raiz = elems.get(0);
        int pUltimo = ultimo.prioridad;
        int pRaiz = raiz.prioridad;
        Traslado vUltimo = ultimo.valor;
        Traslado vRaiz = raiz.valor;
        if (longitud == 1) {
            Traslado ret = elems.remove(0).valor;
            longitud--;
            return ret;
        } else {
            elems.get(0).prioridad = pUltimo;
            elems.get(0).valor = vUltimo;
            elems.get(longitud - 1).prioridad = pRaiz;
            elems.get(longitud - 1).valor = vRaiz;
            if (elems.get(longitud - 1).padre.der == null) {
                elems.get(longitud - 1).padre.izq = null;
            } else {
                elems.get(longitud - 1).padre.der = null;
            }
            elems.get(longitud - 1).padre = null;
            Traslado ret = elems.remove(longitud - 1).valor;
            longitud--;
            heapifyDown(elems.get(0));
            return ret;
        }
    }

    // Ingresado un nuevo elemento al array, lo ubica donde corresponde
    public void heapifyUp(Nodo n) {
        if (n.padre != null) { 
            int comparacion = comparator.compare(n.valor, n.padre.valor);
            int pPadre = n.padre.prioridad;
            int pHijo = n.prioridad;
            Traslado vPadre = n.padre.valor;
            Traslado vHijo = n.valor;
            if (comparacion < 0) {
                n.padre.prioridad = pHijo;
                n.prioridad = pPadre;
                n.padre.valor = vHijo;
                n.valor = vPadre;
                heapifyUp(n.padre);
            }
        }
    }

    public void heapifyDown(Nodo n) {
        int pPadre = n.prioridad;
        Traslado vPadre = n.valor;
        if (n.izq != null && n.der != null) {
            int pIzq = n.izq.prioridad;
            int pDer = n.der.prioridad;
            Traslado vIzq = n.izq.valor;
            Traslado vDer = n.der.valor;
            if (n.izq.prioridad > n.der.prioridad) {
                if (pIzq > pPadre) {
                    n.prioridad = pIzq;
                    n.valor = vIzq;
                    n.izq.prioridad = pPadre;
                    n.izq.valor = vPadre;
                    heapifyDown(n.izq);
                } 
            } else {
                if (pDer > pPadre) {
                    n.prioridad = pDer;
                    n.valor = vDer;
                    n.der.prioridad = pPadre;
                    n.der.valor = vPadre;
                    heapifyDown(n.der);
                }
            }
        } else {
            if (n.izq != null) {
                int pIzq = n.izq.prioridad;
                Traslado vIzq = n.izq.valor;
                if (pIzq > pPadre) {
                    n.prioridad = pIzq;
                    n.valor = vIzq;
                    n.izq.prioridad = pPadre;
                    n.izq.valor = vPadre;
                    heapifyDown(n.izq);
                }
            }
        }
    }

    public String toStringPrioridad() {
        String res = "[";
        int i = 0;
        while (i < longitud) {
            res += elems.get(i).prioridad;
            if (i < longitud - 1) {
                res += ", ";
            }
            i++;
        }
        res += "]";
        return res;
    }

    public String toStringValor() {
        String res = "[";
        int i = 0;
        while (i < longitud) {
            res += elems.get(i).valor;
            if (i < longitud - 1) {
                res += ", ";
            }
            i++;
        }
        res += "]";
        return res;
    }
}
