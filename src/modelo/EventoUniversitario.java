package modelo;

import java.io.*;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;

import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    //atributos
    private static final long serialVersionUID = 1L;
    //atributos de la clase
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala; //creamos la relación con la clase modelo.Sala
    private ArrayList<Actividad> actividades; //creamos la relación con la clase modelo.actividades.Actividad
    private ArrayList<Inscripcion> inscripciones; //creamos la relación con la clase modelo.Inscripcion
    //bloque estático
    static {
        System.out.println("El sistema esta inicializandose");
        cantidadEventos = 0;
    }
    //constructor
    public EventoUniversitario (String id, String titulo, double costoBase, boolean gratuito){
        this.id = id;
        setTitulo(titulo);
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.sala = sala;
        this.actividades = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
        cantidadEventos++;
    }
    //constructor copia
    public EventoUniversitario (EventoUniversitario otro){
        this.id = otro.id;
        setTitulo(otro.titulo);
        this.costoBase = otro.costoBase;
        this.gratuito =otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        this.inscripciones = new ArrayList<>(otro.inscripciones);
        cantidadEventos++;
    }
    //getter and setter
    public String getId() {
        return id;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo) {
        if(titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        } else {
            System.out.println("[❌] El título no puede estar vacío");
        }
    }
    public double getCostoBase() {
        return costoBase;
    }
    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }
    public boolean getGratuito(){
        return gratuito;
    }
    public void setGratuito(boolean gratuito){
        this.gratuito = gratuito;
    }
    public static int getCantidadEventos() {
        return cantidadEventos;
    }
    public static void setCantidadEventos(int cantidadEventos) {
        EventoUniversitario.cantidadEventos = cantidadEventos;
    }
    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    //metodos
    public double calcularCostoBase(){
        double costoTotal = this.costoBase; //decimos esto ya que si un curso no tiene actividad
        for(Actividad a : actividades) { //recorremos la lista actividades
            costoTotal += a.getCupoMaximo() * 200; //ej:200
        }
        return costoTotal;
    }
    public void asignarSala(Sala sala) {
        this.sala = sala;
    }
    public void crearActividad(int id, String titulo, int cupo, String tipo, String datoExtra, boolean boolExtra) {
        if (tipo.equalsIgnoreCase("modelo.actividades.Charla")) { //para crear una charla
            actividades.add(new Charla(id, titulo, cupo, datoExtra));
        } else if (tipo.equalsIgnoreCase("modelo.actividades.Taller")){ //para crear un taller
            actividades.add(new Taller(id, titulo, cupo, boolExtra));
        }
    }
    //método para calcular costo estimado con el 21% de impuestos
    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }
        double costoTotalActividades = 0.0;
        for (Actividad act : actividades) {
            costoTotalActividades += act.calcularCostoMateriales();
        }
        return (costoBase + costoTotalActividades) * 1.21;
    }
    //método para filtrar las actividades por tipo
    public <T extends Actividad> List<T> filtrarActividadesPorTipo (Class<T> tipo) {
        List<T> listaFiltrada = new ArrayList<>();
        for (Actividad act : this.actividades) {
            if (tipo.isInstance(act)) {
                listaFiltrada.add((T) act);
            }
        }
        return listaFiltrada;
    }
    //método para calcular el costo de materiales para la sublista Actividad
    public double calcularCostoMateriales (List<? extends Actividad> actividads) {
        double costoTotal = 0.0;
        for (Actividad act : actividads) {
            costoTotal += act.calcularCostoMateriales();
        }
        return costoTotal;
    }
    //persistencia: guardamos el archivo binario usando objectOutputStream
    public boolean persistirEvento () {
        try (ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("evento.dat"))) {
            oos.writeObject(this); //con ese "this" serializa el evento entero junto a sus actividades e inscripciones
            System.out.println("\n[✅] Datos guardados exitosamente en 'evento.dat'.");
            return true;
        } catch (IOException e) {
            System.out.println("[❌] Error al cargar: " + e.getMessage());
            return false;
        }
    }
    //recuperaciòn: cargamos el archivo binario usando objectInputStream
    public static EventoUniversitario recuperarEvento (String nombreArchivo) { //siempre es "static" porque lo llamamos antes de tener el objeto cargado en memeoria
        try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream(nombreArchivo))) {
            EventoUniversitario eventoCargado = (EventoUniversitario) ois.readObject();
            System.out.println("\n[✅] Datos recuperados exitosamente desde '" + nombreArchivo + "'.");
            return eventoCargado;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[❌] Error al cargar: " + e.getMessage());
            return null;
        }
    }
    //mostramos datos
    public void mostrarDatos(){
        String nombreSala = (sala != null) ? sala.getNombre() : "Sin asignar";
        System.out.println("\nId: " + id + " | Título: " + titulo + " | Costo Base: " + costoBase + " | Gratuito: " + gratuito + " |modelo.Sala: " + nombreSala);
    }
}