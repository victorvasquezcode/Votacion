package com.votacion.votacionsegura.service;

import com.votacion.votacionsegura.model.Eleccion;
import com.votacion.votacionsegura.repository.EleccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EleccionService {
    @Autowired
    private EleccionRepository eleccionRepository;

    public List<Eleccion> getAllElecciones() {
        return eleccionRepository.findAll();
    }

    public Optional<Eleccion> getEleccionById(Long id) {
        return eleccionRepository.findById(id);
    }

    public Eleccion createEleccion(Eleccion eleccion) {
        return eleccionRepository.save(eleccion);
    }

    public Eleccion updateEleccion(Long id, Eleccion eleccion) {
        return eleccionRepository.findById(id).map(existingEleccion -> {
            existingEleccion.setNombre(eleccion.getNombre());
            existingEleccion.setFechaInicio(eleccion.getFechaInicio());
            existingEleccion.setFechaFin(eleccion.getFechaFin());
            return eleccionRepository.save(existingEleccion);
        }).orElseThrow(() -> new RuntimeException("Eleccion not found"));
    }

    public void deleteEleccion(Long id) {
        eleccionRepository.deleteById(id);
    }
}
