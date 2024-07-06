package com.votacion.votacionsegura.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "candidatos")
@Data
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String partido;

    @ManyToOne
    @JoinColumn(name = "eleccion_id")
    private Eleccion eleccion;
}
