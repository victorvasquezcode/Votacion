package com.votacion.votacionsegura.repository;

import com.votacion.votacionsegura.model.LogAuditoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {
    List<LogAuditoria> findByUsuarioId(Long usuarioId);
}