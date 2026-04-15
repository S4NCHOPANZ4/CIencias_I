package co.ciencias.genericlist.model;

public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int tamanio;

    public ListaEnlazada() {
        cabeza = null;
        tamanio = 0;
    }

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        tamanio++; 
    }
    
    public String listar() {
        if (cabeza == null) {
            return "Empty";
        }
        String resultado = "";
        Nodo<T> actual = cabeza;

        while (actual != null) {
            resultado += actual.mostrar() + "\n";
            actual = actual.getSiguiente();
        }

        return resultado;
    }
    public boolean actualizar(int index, T nuevoDato) {
        if (index < 0 || index >= tamanio) return false; 

        Nodo<T> actual = cabeza;
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
            tamanio--; 
            return true;
        }

        Nodo<T> actual = cabeza;
        int i = 0;

        while (actual.getSiguiente() != null) {
            if (i + 1 == index) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
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