package com.votacion.votacionsegura.controller;

import com.votacion.votacionsegura.model.Candidato;
import com.votacion.votacionsegura.model.Eleccion;
import com.votacion.votacionsegura.service.CandidatoService;
import com.votacion.votacionsegura.service.EleccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elecciones")
public class EleccionController {
    @Autowired
    private EleccionService eleccionService;

    @Autowired
    private CandidatoService candidatoService;

    @GetMapping
    public List<Eleccion> getAllElecciones() {
        return eleccionService.getAllElecciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eleccion> getEleccionById(@PathVariable Long id) {
        return eleccionService.getEleccionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Eleccion createEleccion(@RequestBody Eleccion eleccion) {
        return eleccionService.createEleccion(eleccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Eleccion> updateEleccion(@PathVariable Long id, @RequestBody Eleccion eleccion) {
        return ResponseEntity.ok(eleccionService.updateEleccion(id, eleccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEleccion(@PathVariable Long id) {
        eleccionService.deleteEleccion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/candidatos")
    public ResponseEntity<List<Candidato>> getCandidatosByEleccionId(@PathVariable Long id) {
        return ResponseEntity.ok(candidatoService.findByEleccionId(id));
    }
}
