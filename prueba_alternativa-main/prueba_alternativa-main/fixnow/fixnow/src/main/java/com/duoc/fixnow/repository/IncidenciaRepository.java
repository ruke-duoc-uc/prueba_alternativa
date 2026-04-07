package com.duoc.fixnow.repository;

import com.duoc.fixnow.model.Incidencia;
import com.duoc.fixnow.service.IncidenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IncidenciaRepository {
    public List<Incidencia> listaIncidencias = new ArrayList<>();
    public IncidenciaRepository(){
        listaIncidencias.add(new Incidencia("Titulo","Descripcion","estado","prioridad","Rodolfo","2/05/2025"));
    }
    // Metodos Get
    public ResponseEntity<?> listarIncidencias() {
            return ResponseEntity.ok(listaIncidencias);
        }
    // Agregar excepcion por id nulo
    /* Busqueda por id
    Utilizo un for para explorar toda la lista, estableciendo una incidencia temporal llamada "encontrado"
    Cuando la incidencia tenga una id que coincida con la que busca el usuario, el if da paso al return con la incidencia encontrada
    */
    public ResponseEntity<?> buscarIncidenciaPorId(Integer id){
        try{
            for(Incidencia incidencia : listaIncidencias)
                if (incidencia.getId().equals(id)){
                    Incidencia encontrado;
                    encontrado = incidencia;
                    return ResponseEntity.ok(encontrado);
                }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe una incidencia con la id ingresada");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el incidente");
        }
    }
    public ResponseEntity<?> buscarPorEstado(String estado){
        try{
            ArrayList<Incidencia> listaEstado = new ArrayList<>();
            for (Incidencia incidencia : listaIncidencias){
                if (incidencia.getEstado().equals(estado)){
                    listaEstado.add(incidencia);
                }
            }
            return ResponseEntity.ok(listaEstado);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los incidentes");
        }
    }

    // Posteo
    // Optimizar adecuadamente el codigo despues
    public ResponseEntity<?> agregarIncidencia (Incidencia incidencia){
        try {
            listaIncidencias.add(incidencia);
            return ResponseEntity.ok(incidencia);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el incidente");
        }
    }

    //  Actualizacion
    /* El codigo fue creado para actualizar todos los datos que no sean la id
    Pero creo que podria ignorar ciertads partes en el controller, aunque perderia tiempo
    agregando todas las permutaciones
     */
    public ResponseEntity<?> actualizarIncidencia (Integer id, Incidencia cuerpo){
        try {
            for (Incidencia incidencia : listaIncidencias)
                if (incidencia.getId().equals(id)) {
                    incidencia.setTitulo(cuerpo.getTitulo());
                    incidencia.setDescripcion(cuerpo.getDescripcion());
                    incidencia.setEstado(cuerpo.getEstado());
                    incidencia.setPrioridad(cuerpo.getPrioridad());
                    incidencia.setUsuarioReportante(cuerpo.getUsuarioReportante());
                    incidencia.setFechaRegistro(cuerpo.getFechaRegistro());
                    return ResponseEntity.ok(incidencia.getClass());
                }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe una incidencia con la id ingresada");
        }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el incidente");
    }
    }
    //Eliminacion
    // Es muy probable que el codigo este malo
    public ResponseEntity<?> eliminarIncidente(Integer id){
        try{
            for (Incidencia incidencia : listaIncidencias){
                if (incidencia.getId().equals(id)){
                    return ResponseEntity.ok(listaIncidencias.remove(incidencia));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontro el id ingresado");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el incidente");
        }
    }
}

