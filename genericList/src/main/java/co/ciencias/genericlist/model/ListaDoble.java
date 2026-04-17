package co.ciencias.genericlist.model;

public class ListaDoble<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamanio;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public void agregar(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }

        tamanio++;
    }

    public String listar() {
        if (cabeza == null) return "Empty";

        String resultado = "";
        NodoDoble<T> actual = cabeza;

        while (actual != null) {
            resultado += actual.getDato() + " <-> ";
            actual = actual.getSiguiente();
        }

        return resultado + "null";
    }

    public String listarReversa() {
        if (cola == null) return "Empty";

        String resultado = "";
        NodoDoble<T> actual = cola;

        while (actual != null) {
            resultado += actual.getDato() + " <-> ";
            actual = actual.getAnterior();
        }

        return resultado + "null";
    }

    public boolean actualizar(int index, T nuevoDato) {
        if (index < 0 || index >= tamanio) return false;

        NodoDoble<T> actual = cabeza;
        int i = 0;

        while (actual != null) {
            if (i == index) {
                actual.setDato(nuevoDato);
                return true;
            }
            actual = actual.getSiguiente();
            i++;
        }
        return false;
    }

    public boolean eliminar(int index) {
        if (index < 0 || index >= tamanio) return false;

        if (index == 0) {
            cabeza = cabeza.getSiguiente();
            if (cabeza != null) {
                cabeza.setAnterior(null);
            } else {
                cola = null;
            }
            tamanio--;
            return true;
        }

        NodoDoble<T> actual = cabeza;
        int i = 0;

        while (actual != null) {
            if (i == index) {
                NodoDoble<T> anterior = actual.getAnterior();
                NodoDoble<T> siguiente = actual.getSiguiente();

                if (siguiente != null) {
                    siguiente.setAnterior(anterior);
                } else {
                    cola = anterior; 
                }

                anterior.setSiguiente(siguiente);
                tamanio--;
                return true;
            }
            actual = actual.getSiguiente();
            i++;
        }
        return false;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }
}