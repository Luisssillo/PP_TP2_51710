package modelo.certificacion;

import modelo.Estudiante;

public interface Certificable { //las interfaces tienen siempre atributos y metodos publicos y constantes
    String ENTIDAD_EMISORA = "UTN - FRM";
    //metodos
    String generarCertificado (Estudiante estudiante); //lo utilizaremos en las clase Taller y en la clase Curso
}
