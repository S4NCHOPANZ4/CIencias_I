package co.ciencias.personas.controller;

import co.ciencias.personas.model.Directorio;
import co.ciencias.personas.model.Persona;
import co.ciencias.personas.view.VistaGUI;
import javax.swing.SwingUtilities;

public class Controller {
    private Directorio directory;
    private VistaGUI vista;

    public Controller() {
        directory = new Directorio();
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            vista = new VistaGUI(this);
            vista.setVisible(true);
        });
    }


    public void ingresarPersona(String nombre, int edad, double altura) {
        Persona x = new Persona(nombre, edad, altura);
        if (directory.addPersona(x)) {
            vista.agregarFilaTabla(nombre, edad, altura);
            vista.limpiarFormulario();
        } else {
            vista.mostrarError("Límite del directorio alcanzado. No se puede agregar más personas.");
        }
    }


    public void ordenarPorEdad() {
        directory.sortData();
        refrescarTabla();
    }


    private void refrescarTabla() {
        vista.limpiarTabla();
        String raw = directory.viewAll();
        if (raw.equals("Empty")) return;

        Persona[] personas = directory.getData();
        int count = directory.getOccupied();
        for (int i = 0; i < count; i++) {
            vista.agregarFilaTabla(
                personas[i].getName(),
                personas[i].getAge(),
                personas[i].getHeight()
            );
        }
    }
}