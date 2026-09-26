package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

import java.io.Serializable;

public class Taller extends Actividad implements Serializable, Certificable {
    //atributos
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    private boolean requiereNotebook;
    //constructor
    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }
    //constructor copia
    public Taller(Actividad otro, boolean requiereNotebook) {
        super(otro);
        this.requiereNotebook = requiereNotebook;
    }
    //getter and setter
    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }
    public void setRequiereNotebook(boolean requiereNotebook) {
        this.requiereNotebook = requiereNotebook;
    }
    //metodos
    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000.0 : 2000.0; //5000 si requiere notebook, 2000 si no
    }
    @Override
    public String getTipo() {
        return "modelo.actividades.Taller";
    }
    //implementaciones
    @Override
    public String generarCertificado (Estudiante estudiante) {
        return "   CERTIFICADO DE TALLER\n" +
                "Emisor: " + ENTIDAD_EMISORA + "\n" +
                "Acredita que el estudiante: " + estudiante.getNombre() + " (legajo: " + estudiante.getLegajo() + ")\n" +
                "Aprobó el taller: " + getTitulo();
    }
}
