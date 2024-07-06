package com.votacion.votacionsegura.repository;

import com.votacion.votacionsegura.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findByEleccionId(Long eleccionId);
}
