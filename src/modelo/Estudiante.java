package modelo;

import java.io.Serializable;

public class Estudiante implements Serializable {
    //atributo
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    private String legajo;
    private String nombre;
    //constructor
    public Estudiante (String legajo, String nombre) {
        this.legajo = legajo;
        this.nombre = nombre;
    }
    //constructor copia
    public Estudiante (Estudiante otro) {
        this.legajo = otro.legajo;
        this.nombre = otro.nombre;
    }
    //getter and setter

    public String getLegajo() {
        return legajo;
    }
    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //mostrar datos
    public void mostrarEstudiante () {
        System.out.println("\nLegajo: " + legajo + " | Nombre: " + nombre);
    }
}