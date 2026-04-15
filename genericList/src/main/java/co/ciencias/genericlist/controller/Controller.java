package co.ciencias.genericlist.controller;

import co.ciencias.genericlist.model.ListaEnlazada;
import co.ciencias.genericlist.view.VistaConsola;

public class Controller {

    private VistaConsola vista;
    private ListaEnlazada<Object> lista;
    private int tipoDato; 

    public Controller() {
        vista = new VistaConsola();
        lista = new ListaEnlazada<>();
    }

    public void run() {

        seleccionarTipo(); 

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione opcion:");

            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    actualizar();
                    break;
                case 4:
                    eliminar();
                    break;
                case 0:
                    vista.mostrarInformacion("Saliendo...");
                    break;
                default:
                    vista.mostrarInformacion("Opción inválida");
            }

        } while (opcion != 0);
    }

    private void seleccionarTipo() {
        vista.mostrarInformacion(
                "Seleccione tipo de dato:\n"
                + "1. Integer\n"
                + "2. Double\n"
                + "3. String"
        );

        tipoDato = leerEntero("Opcion:");

        if (tipoDato < 1 || tipoDato > 3) {
            vista.mostrarInformacion("Tipo invalido, se usara String por defecto");
            tipoDato = 3;
        }
    }

    private void mostrarMenu() {
        vista.mostrarInformacion(
                "\n--- CRUD Lista Enlazada --- "
                + "\n1. Agregar "
                + "\n2. Listar"
                + "\n3. Actualizar"
                + "\n4. Eliminar "
                + "\n0. Salir"
        );
    }

    private void agregar() {
        Object dato = leerDatoTipado("Ingrese dato:");

        if (dato != null) {
            lista.agregar(dato);
        }

        continuar();
    }

    private void listar() {
        vista.mostrarInformacion(lista.listar());
        continuar();
    }

    private void actualizar() {
        int index = leerEntero("Indice a actualizar:");
        Object dato = leerDatoTipado("Nuevo dato:");

        if (dato != null) {
            if (!lista.actualizar(index, dato)) {
                vista.mostrarInformacion("Indice invalido/No encontrado en la lista");
            }
        }

        continuar();
    }

    private void eliminar() {
        int index = leerEntero("Indice a eliminar:");

        if (!lista.eliminar(index)) {
            vista.mostrarInformacion("Indice invalido/No encontrado en la lista");
        }

        continuar();
    }

    private int leerEntero(String mensaje) {
        try {
            return Integer.parseInt(vista.leerDato(mensaje));
        } catch (NumberFormatException e) {
            vista.mostrarInformacion("Debe ingresar un numero valido");
            return -1;
        }
    }

    private Object leerDatoTipado(String mensaje) {
        String input = vista.leerDato(mensaje);

        try {
            switch (tipoDato) {
                case 1:
                    return Integer.parseInt(input);
                case 2:
                    return Double.parseDouble(input);
                case 3:
                    return input;
                default:
                    return input;
            }
        } catch (Exception e) {
            vista.mostrarInformacion("Error en el tipo de dato");
            return null;
        }
    }

    private void continuar() {
        vista.leerDato("Enter para continuar...");
    }
}
