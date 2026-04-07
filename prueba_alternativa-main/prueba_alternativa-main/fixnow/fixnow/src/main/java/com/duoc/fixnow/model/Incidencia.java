package com.duoc.fixnow.model;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Incidencia {
    // Agregar metodo de autoescaldo
    /* se utiliza un numero que aumenta a medida que se agregan nuevos
    incidentes, permitiendo un escalado automatico
     */
    private static int contador = 1;
    private Integer id;

    private String titulo;

    private String descripcion;

    private String estado;

    private String prioridad;

    private String usuarioReportante;

    private String fechaRegistro;
 //Debido a la automatizacion de arriba, no solicito el id en el constructor
    public Incidencia(String titulo, String descripcion, String estado, String prioridad, String usuarioReportante, String fechaRegistro) {
        this.id = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioReportante = usuarioReportante;
        this.fechaRegistro = fechaRegistro;
    }

    public Incidencia() {
    }
}
