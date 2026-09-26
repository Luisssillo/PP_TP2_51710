package modelo.actividades;

import exepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Actividad implements Serializable {
    //atributos
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    protected int id;
    protected String titulo;
    protected int cupoMaximo;
    protected static final int cupoMinimo = 1; //ej:1
    protected ArrayList<Inscripcion> inscripciones; //creamos la relación con la clase Inscripción
    protected Estudiante estudiante; //creamos la relación con la clase modelo.Estudiante
    //constructor
    public Actividad (int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>(); //inicializamos la lista
        this.estudiante = estudiante;
    }
    //constructor copia
    public Actividad (Actividad otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.cupoMaximo = otro.cupoMaximo;
        this.inscripciones = new ArrayList<>(otro.inscripciones);
        this.estudiante = otro.estudiante;
    }
    //getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getCupoMaximo() {
        return cupoMaximo;
    }
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    public int getCupoMinimo() {
        return cupoMinimo;
    }
    //metodos
    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (this.inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException("La actividad ha superado el máximo de cupos.");
        }
        Inscripcion unInscripto = new Inscripcion(LocalDate.now(), "Inscripto", estudiante);
        this.inscripciones.add(unInscripto); //agregamos a la lista de inscripciones
        System.out.println("\n[✅] Alumnos guardados con exito: " + this.inscripciones.size() + ".");
        return unInscripto;
    }
    public ArrayList<Inscripcion> getInscripciones(){
        return inscripciones;
    }
    public void mostrarInscripciones(){
        for (Inscripcion ins : inscripciones) {
            ins.mostrarInscripcion(); //mostramos al fecha, estado y estudiante
        }
    }
    //metodo final
    public final void mostrarIdentificacion() {
        System.out.println("\nId: " + id + " | Titulo: " + titulo + " | Tipo: " + getTipo() + " | Costo materiales: $" + calcularCostoMateriales());
    }
    //metodos abstractos
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();
}