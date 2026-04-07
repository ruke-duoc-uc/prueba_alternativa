package com.duoc.fixnow.service;

import com.duoc.fixnow.model.Incidencia;
import com.duoc.fixnow.repository.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
// Quizas son private

public class IncidenciaService {
    @Autowired
    public IncidenciaRepository incidenciaRepository;
    public ResponseEntity<?> getLista(){return incidenciaRepository.listarIncidencias();}
    public ResponseEntity<?> addIncidencia(Incidencia incidencia) {return incidenciaRepository.agregarIncidencia(incidencia);}
    public ResponseEntity<?> deleteIncidencia(Integer id){return incidenciaRepository.eliminarIncidente(id);}
    public ResponseEntity<?> getById(Integer id){return incidenciaRepository.buscarIncidenciaPorId(id);}
    public ResponseEntity<?> getByEstado(String estado){return incidenciaRepository.buscarPorEstado(estado);}
    public ResponseEntity<?> updateIncidencia(Integer id, Incidencia cuerpo){return incidenciaRepository.actualizarIncidencia(id,cuerpo);}
}
