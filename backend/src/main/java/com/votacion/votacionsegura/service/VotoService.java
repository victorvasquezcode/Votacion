package com.votacion.votacionsegura.service;

import com.votacion.votacionsegura.model.Voto;
import com.votacion.votacionsegura.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {
    @Autowired
    private VotoRepository votoRepository;

    public List<Voto> getAllVotos() {
        return votoRepository.findAll();
    }

    public Optional<Voto> getVotoById(Long id) {
        return votoRepository.findById(id);
    }

    public Voto createVoto(Voto voto) {
        return votoRepository.save(voto);
    }

    public boolean hasUserVotedInElection(Long userId, Long electionId) {
        List<Voto> votos = votoRepository.findByUsuarioIdAndEleccionId(userId, electionId);
        return !votos.isEmpty();
    }

    public List<Voto> findVotesByUserId(Long userId) {
        return votoRepository.findByUsuarioId(userId);
    }

    public Voto updateVoto(Long id, Voto voto) {
        return votoRepository.findById(id).map(existingVoto -> {
            existingVoto.setSeleccion(voto.getSeleccion());
            existingVoto.setOpcion(voto.getOpcion());
            return votoRepository.save(existingVoto);
        }).orElseThrow(() -> new RuntimeException("Voto not found"));
    }

    public void deleteVoto(Long id) {
        votoRepository.deleteById(id);
    }
}
