package com.votacion.votacionsegura.repository;

import com.votacion.votacionsegura.model.Voto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findByUsuarioIdAndEleccionId(Long usuarioId, Long eleccionId);
    List<Voto> findByUsuarioId(Long usuarioId);
}