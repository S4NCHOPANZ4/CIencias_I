package co.ciencias.personas.model;

public class Directorio {

    private int size;
    private Persona data[];
    private int occupied;

    public Directorio() {
        size = 4;
        data = new Persona[size];
        occupied = 0;
    }

    public boolean addPersona(Persona x) {
        if (occupied < size) {
            data[occupied] = x;
            occupied++;
            return true;
        }
        return false;
    }

    public String viewAll() {
        String rta = "";
        if (occupied > 0) {
            for (int i = 0; i < occupied; i++) {
                rta = rta + data[i] + "\n\n";
            }
        } else {
            rta = "Empty";
        }
        return rta;
    }

    public void sortData() {
        for (int i = 0; i < occupied - 1; i++) {
            for (int j = 0; j < occupied - i - 1; j++) {
                if (data[j].getAge() > data[j + 1].getAge()) {
                    Persona aux = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = aux;
                }
            }
        }
    }


    public Persona[] getData() {
        return data;
    }

    public int getOccupied() {
        return occupied;
    }

    public int getSize() {
        return size;
    }
}