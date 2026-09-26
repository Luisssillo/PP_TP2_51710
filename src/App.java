import exepciones.CupoExcedidoException;
import modelo.*;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // creamos una lista dinámica de estudiantes
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

        //creamos la copia de la lista
        ArrayList<Estudiante> copiaListaEstudiantes = new ArrayList<>(listaEstudiantes);

        //creamos a los 3 estudiantes
        listaEstudiantes.add(new Estudiante("51710", "Luis")); //estudiante 0
        listaEstudiantes.add(new Estudiante("63442", "Alfredo")); //estudiante 1
        listaEstudiantes.add(new Estudiante("48988", "Analia")); //estudiante 2
        listaEstudiantes.add(new Estudiante("59488", "Roberto")); //estudiante 3

        //creamos una lista dinámica de los eventos
        ArrayList<EventoUniversitario> listaEventos = new ArrayList<>();

        //creamos la copia de la lista
        ArrayList<EventoUniversitario> copiaListaEventos = new ArrayList<>(listaEventos);

        //creamos a los eventos
        listaEventos.add(new EventoUniversitario("EVT-001", "Talento en acción", 0.0, true)); //evento 0
        listaEventos.add(new EventoUniversitario("EVT-002", "Expo ideas", 50000.0, false)); //evento 1

        // creamos una lista dinámica de las salas
        ArrayList<Sala> listaSalas = new ArrayList<>();

        //creamos la copia de la sala
        ArrayList<Sala> copiaListaSalas = new ArrayList<>(listaSalas);

        //creamos a las salas
        listaSalas.add(new Sala(1, "LISUM")); //sala 0
        listaSalas.add(new Sala(2, "SALA DE DIBUJO")); //sala 1

        //asignamos salas a los eventos de la lista
            listaEventos.get(0).asignarSala(listaSalas.get(0));
            listaEventos.get(1).asignarSala(listaSalas.get(1));

        // creamos una lista dinámica de charla, taller y curso
        ArrayList<Actividad> listaCharlas = new ArrayList<>();
        ArrayList<Actividad> listaTalleres = new ArrayList<>();
        ArrayList<Actividad> listaCursos = new ArrayList<>();

        //creamos la copia de la lista
        ArrayList<Actividad> copiaListaCharlas = new ArrayList<>(listaCharlas);
        ArrayList<Actividad> copiaListaTalleres = new ArrayList<>(listaTalleres);
        ArrayList<Actividad> copiaListaCursos = new ArrayList<>(listaCursos);

        //creamos 3 actividades: 1 charla, 1 taller y 1 curso
        listaCharlas.add(new Charla(1, "Programar", 2, "Ing. Pèrez")); //charla 0
        listaTalleres.add(new Taller(2, "Investigacion IA", 5, true)); //taller 0; requiere notebook $5000
        listaCursos.add(new Curso(3, "Ciberseguridad", 3, 5)); //curso 0

        //agregamos las actividades a los eventos
        listaEventos.get(0).getActividades().add(listaCharlas.get(0)); //agregado al evento 0
        listaEventos.get(0).getActividades().add(listaTalleres.get(0)); //agregado al evento 0
        listaEventos.get(1).getActividades().add(listaCursos.get(0)); //agregado al evento 1


        //bloque try-catch (intentamos ejecutar el codigo peligroso)
        System.out.println("\n----- PROBANDO INSCRIPCIONES Y CAPACIDAD -----");
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            //intentamos inscribir a charla 0
            try {
                System.out.println("\nIntentando inscribir a '" + listaEstudiantes.get(i).getNombre() + " (legajo: " + listaEstudiantes.get(i).getLegajo() + ")' en: '" + listaCharlas.get(0).getTitulo() + "'.");
                listaCharlas.get(0).inscribir(listaEstudiantes.get(i)); //asignamos los estudiantes a la charla 0
                System.out.println("[✅] Inscripto correctamente.");
            } catch (CupoExcedidoException e) {
                //atrapamos el error para que el programa no explote
                System.out.println("\n[❌] Encontramos el problema: " + e.getMessage());
            } finally {
                //se ejecuta siempre, haya o no error
                System.out.println("-> Evaluaciòn de cupo finalizada.");
            }

            //intentamos inscribir a taller 0
            try {
                System.out.println("\nIntentando inscribir a '" + listaEstudiantes.get(i).getNombre() + " (legajo: " + listaEstudiantes.get(i).getLegajo() + ")' en: " + listaTalleres.get(0).getTitulo());
                listaTalleres.get(0).inscribir(listaEstudiantes.get(i)); //asignamos los estudiantes al taller 0
                System.out.println("[✅] Inscripto correctamente.");
            } catch (CupoExcedidoException e) {
                //atrapamos el error para que el programa no explote
                System.out.println("\n[❌] Encontramos el problema " + e.getMessage());
            } finally {
                //se ejecuta siempre, haya o no error
                System.out.println("-> Evaluaciòn de cupo finalizada.");
            }

            //intentando inscribir a curso 0
            try {
                System.out.println("\nIntentando inscribir a '" + listaEstudiantes.get(i).getNombre() + " (legajo: " + listaEstudiantes.get(i).getLegajo() + ")' en: '" + listaCursos.get(0).getTitulo() + "'.");
                listaCursos.get(0).inscribir(listaEstudiantes.get(i)); //asignamos los estudiantes al curso 0
            } catch (CupoExcedidoException e) {
                //atrapamos el eror para que el programa no explote
                System.out.println("\n[❌] Encontramos el problema " + e.getMessage());
            } finally {
                //se ejecuta siempre, haya o no error
                System.out.println("\n-> Evaluación de cupo finalizada.");
            }
        }

        //emitimos los certificados
        System.out.println("\n----- EMISIÓN DE CERTIFICADOS DE ASISTENCIA -----");
        for (EventoUniversitario evento : listaEventos) {
            for (Actividad act : evento.getActividades()) {
                //preguntamos si la actividad es certificable (sabemos que Talleres y Curos si, pero Charla no)
                if (act instanceof  modelo.certificacion.Certificable) {
                    modelo.certificacion.Certificable actCertificable = (modelo.certificacion.Certificable) act;
                    for (Inscripcion ins : act.getInscripciones()) {
                        //generamos e imprimimos el certificado
                        System.out.println("\n" + actCertificable.generarCertificado(ins.getEstudiante()));
                        System.out.println("\n-----------------------------------------------------");
                    }
                }
            }
        }


        //filtramos y mostramos el costo de materiales
        System.out.println("\n----- FILTRADO Y COSTO DE MATERIALES -----");
        for (EventoUniversitario evento : listaEventos) {
            System.out.println("\nEvento: '" + evento.getTitulo() + "'");

            //filtramos por tipo concreto
            List<Charla> charlas = evento.filtrarActividadesPorTipo(Charla.class);
            List<Taller> talleres = evento.filtrarActividadesPorTipo(Taller.class);
            List<Curso> cursos = evento.filtrarActividadesPorTipo(Curso.class);

            //cantidad por tipo
            System.out.println(" - Charlas: " + charlas.size() + " | Costo materiales: $" + evento.calcularCostoMateriales(charlas));
            System.out.println(" - Talleres: " + talleres.size() + " | Costo materiales: $" + evento.calcularCostoMateriales(talleres));
            System.out.println(" - Cursos: " + cursos.size() + " | Costo materiales: $" + evento.calcularCostoMateriales(cursos));
        }


        //persistencia y recuperaciòn
        System.out.println("\n----- PERSISTENCIA DE DATOS -----");

        //guardamos el evento
        listaEventos.get(0).persistirEvento();

        //leemos el archivo recuperado con manejos de errores
        try {
            EventoUniversitario eventoCargado = EventoUniversitario.recuperarEvento("evento.dat");
            if (eventoCargado != null) {
                System.out.println("\n[✅] Evento cargado desde disco. Datos del evento: ");
                eventoCargado.mostrarDatos();
            }
        } catch (Exception e) {
                System.out.println("[❌] Ocurriò un fallo en la lectura: " + e.getMessage());
        } finally {
                //se ejecuta siempre, haya o no error
                System.out.println("\n-> Operaciòn de lectura de archivo finalizada." );
        }


        // mostramos el resumen de datos por eventos
        System.out.println("\n---------- EVENTOS UNIVERSITARIOS ----------");
        for(EventoUniversitario evento : listaEventos){
            evento.mostrarDatos();
            System.out.println("\nCosto estimado (con impuestos): $" + evento.calcularCostoEstimado());
            System.out.println("\nActividades: ");
            for (Actividad act : evento.getActividades()) {
                act.mostrarIdentificacion(); // Método final
                act.mostrarInscripciones();
            }
            System.out.println("\n-----------------------------------------------------");
        }
        //mostramos la copia de la lista
        System.out.println("\n---------- COPIA ----------");
        for(EventoUniversitario evento : copiaListaEventos){
            evento.mostrarDatos();
        }

        // G. mostramos la cantidad de eventos creados
        System.out.println("\nCANTIDAD DE EVENTOS CREADOS: " + EventoUniversitario.getCantidadEventos());
    }
}