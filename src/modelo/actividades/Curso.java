package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

import java.io.Serializable;

public class Curso extends Actividad implements Serializable, Certificable {
    //atributo
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    private int nivel;
    //constructor
    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }
    //constructor copia
    public Curso(Actividad otro, int nivel) {
        super(otro);
        this.nivel = nivel;
    }
    //getter and setter
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    //metodos abstractos
    @Override
    public double calcularCostoMateriales() {
        return 0.0;
    }
    @Override
    public String getTipo() {
        return "modelo.actividades.Curso";
    }
    //implementaciones
    @Override
    public String generarCertificado (Estudiante estudiante) {
        return "   CERTIFICADO DE CURSO\n" +
                "Emisor: " + ENTIDAD_EMISORA + "\n" +
                "Acredita que el estudiante: " + estudiante.getNombre() + " (legajo: " + estudiante.getLegajo() + ")\n" +
                "Aprobó el curso: " + getTitulo() + " - Nivel: " + this.nivel;
    }
}
