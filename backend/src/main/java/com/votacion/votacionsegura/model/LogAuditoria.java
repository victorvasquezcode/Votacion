package com.votacion.votacionsegura.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_auditoria")
@Data

public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accion;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private LocalDateTime fecha;

}