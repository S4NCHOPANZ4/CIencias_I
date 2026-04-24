package co.ciencias.genericlist.model;

public class ListaCircular<T> {
    private Nodo<T> cabeza;
    private int tamanio;
    
    public ListaCircular(){
        cabeza = null;
        tamanio = 0;
    }
    
    public void agregar(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if(tamanio == 0){
            nuevo.setSiguiente(nuevo);
            cabeza = nuevo;
        }else{
            Nodo<T> current = cabeza;
            while(current.getSiguiente() != cabeza){
                current = current.getSiguiente();
            }
            current.setSiguiente(nuevo);
            nuevo.setSiguiente(cabeza);
        }
        tamanio++;
    }
    
    public void eliminar(){
        if(tamanio == 0) return;
        Nodo<T> current = cabeza;
        if(current.getSiguiente() == cabeza){
            cabeza = null;
        }else{
        while(current.getSiguiente().getSiguiente() != cabeza){
            current = current.getSiguiente();
        }
        current.setSiguiente(cabeza);   
        }
        tamanio--;
       
    }
}
