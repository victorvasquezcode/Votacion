package com.votacion.votacionsegura.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "votos")
@Data
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eleccion_id")
    private Eleccion eleccion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String seleccion; // Candidato seleccionado

    private String opcion; // Opción seleccionada (si es necesario)
}
