package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    //atributos
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante; //creamos la relacion con la clase modelo.Estudiante
    //cosntructor
    public Inscripcion (LocalDate fecha, String estado, Estudiante estudiante) {
        this.fecha = fecha;
        this.estado = estado;
        this.estudiante = estudiante;
    }
    //cosntructor copia
    public Inscripcion (Inscripcion otro) {
        this.fecha = otro.fecha;
        this.estado = otro.estado;
        this.estudiante = otro.estudiante;
    }
    //getter and setter
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    //mostramos datos
    public void mostrarInscripcion () {
        String nombreEst = (estudiante != null) ? estudiante.getNombre() : "Sin alumno";
        System.out.println("\nFecha: " + fecha + " | Estado: " + estado + " | Alumno: " + nombreEst);
    }
}