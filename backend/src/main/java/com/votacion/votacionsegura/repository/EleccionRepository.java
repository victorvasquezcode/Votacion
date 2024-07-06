package com.votacion.votacionsegura.repository;

import com.votacion.votacionsegura.model.Eleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleccionRepository extends JpaRepository<Eleccion, Long> {
}
