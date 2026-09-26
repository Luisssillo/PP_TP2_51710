package modelo;

import java.io.Serializable;

public class Sala implements Serializable {
    //atributos
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    private int id;
    private String nombre;
    //constructor
    public Sala (int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    //constructor copia
    public Sala (Sala otro) {
        this.id = otro.id;
        this.nombre = otro.nombre;
    }
    //getter amd setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //mostramos datos
    public void mostrarSalas(){
        System.out.println("Id: " + id + " | Nombre: " + nombre);
    }
}