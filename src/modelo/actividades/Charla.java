package modelo.actividades;

import java.io.Serializable;

public class Charla extends Actividad implements Serializable {
    //atributos
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    private String disertante;
    //constructor
    public Charla(int id, String titulo, int cupoMaximo, String disertante) {
        super(id, titulo, cupoMaximo);
        this.disertante = disertante;
    }
    //constructor copia
    public Charla(Actividad otro, String disertante) {
        super(otro);
        this.disertante = disertante;
    }
    //getter and setter
    public String getDisertante() {
        return disertante;
    }
    public void setDisertante(String disertante) {
        this.disertante = disertante;
    }
    //metodos
    @Override
    public double calcularCostoMateriales() {
        return 0.0; //las charlas son gratuitas
    }
    @Override
    public String getTipo() {
        return "modelo.actividades.Charla";
    }
}
