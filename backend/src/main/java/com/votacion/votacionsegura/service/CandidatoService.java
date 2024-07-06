package com.votacion.votacionsegura.service;

import com.votacion.votacionsegura.model.Candidato;
import com.votacion.votacionsegura.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public List<Candidato> findByEleccionId(Long eleccionId) {
        return candidatoRepository.findByEleccionId(eleccionId);
    }
}
