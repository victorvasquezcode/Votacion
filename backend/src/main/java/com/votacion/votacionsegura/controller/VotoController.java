package com.votacion.votacionsegura.controller;

import com.votacion.votacionsegura.model.Voto;
import com.votacion.votacionsegura.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votos")
public class VotoController {
    @Autowired
    private VotoService votoService;

    @GetMapping
    public List<Voto> getAllVotos() {
        return votoService.getAllVotos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voto> getVotoById(@PathVariable Long id) {
        return votoService.getVotoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createVoto(@RequestBody Voto voto) {
        boolean hasVoted = votoService.hasUserVotedInElection(voto.getUsuario().getId(), voto.getEleccion().getId());
        if (hasVoted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya ha votado en esta elección.");
        }
        votoService.createVoto(voto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Voto registrado con éxito.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voto> updateVoto(@PathVariable Long id, @RequestBody Voto voto) {
        return ResponseEntity.ok(votoService.updateVoto(id, voto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoto(@PathVariable Long id) {
        votoService.deleteVoto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Voto>> getUserVotes(@PathVariable Long userId) {
        List<Voto> votos = votoService.findVotesByUserId(userId);
        return ResponseEntity.ok(votos);
    }
}