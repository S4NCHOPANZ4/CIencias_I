package co.ciencias.personas.controller;

import co.ciencias.personas.model.Directorio;
import co.ciencias.personas.model.Persona;
import co.ciencias.personas.view.VistaConsola;

public class Controller {
    private Directorio directory;
    private VistaConsola vista;

    public Controller(){
        directory = new Directorio();
        vista = new VistaConsola();
    }
    public void run(){

    }
    public void ingresarPersona(){
        Persona x = new Persona();
        String aux  = vista.leerDato("Type person's name: ");
        x.setName(aux);
        int e = Integer.parseInt(vista.leerDato("Type person's age: "));
        x.setAge(e);
        if(directory.addPersona(x)){
            vista.mostrarInformacion("Person added succesfully.");
        }else{
            vista.mostrarInformacion("Error adding person limit reached.");
        }
    }


}
