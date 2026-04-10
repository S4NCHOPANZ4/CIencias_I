package co.ciencias.personas.model;


public class Persona {
    private String name;
    private int age;
    private double height;

    public Persona(){
        name = "";
        age = 0;
        height = 0.0;
    }
    public Persona(String name,  int age, double height){
        this.name = name;
        this.age = age;
        this.height = height;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public double getHeight(){
        return height;
    }
    public void setHeight(double height){
        this.height = height;
    }

    @Override
    public String toString() {
        return "Persona" + 
        "\nName: " + name + 
        "\nAge: " + age + 
        "\nHeight: " + height;
    }
    
}
