package com.duoc.fixnow.controller;

import com.duoc.fixnow.model.Incidencia;
import com.duoc.fixnow.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/incidencias")
// Quizas son private
// quiza los try catch son aca y no en el repository
public class IncidenciaController {
    @Autowired
    public IncidenciaService incidenciaService;
    @GetMapping
    @RequestMapping("/todos")
    public ResponseEntity<?> listarTodos(){
        try {
            return ResponseEntity.ok(incidenciaService.getLista());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo mostrar la lista");
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try {
            if (id == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se debe entregar un id para buscar");}
            return ResponseEntity.ok(incidenciaService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo buscar el incidente");
        }
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado){
        try {
            if (estado == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se debe entregar un id para buscar");}
            return ResponseEntity.ok(incidenciaService.getByEstado(estado));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo buscar el incidente");
        }
    }
    @PostMapping
    @RequestMapping("/agregarIncidente")
    public ResponseEntity<?> agregarIncidente(@RequestBody Incidencia incidencia){
        try {
            if(incidencia.getTitulo() == null|| incidencia.getDescripcion()== null|| incidencia.getEstado()== null||
            incidencia.getPrioridad()== null||incidencia.getUsuarioReportante()== null||incidencia.getFechaRegistro() == null){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se deben ingresar todos los datos para ingresar un incidente");
            }
        return incidenciaService.addIncidencia(incidencia);
    }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el incidente");
        }
    }
    @DeleteMapping
    @RequestMapping("/eliminarIncidente/{id}")
    public ResponseEntity<?> eliminarIncidente(@PathVariable Integer id){
        try {
            if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe ingresar un id para buscar un incidente");}
            return ResponseEntity.ok(incidenciaService.deleteIncidencia(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el incidente");
        }
    }
}
