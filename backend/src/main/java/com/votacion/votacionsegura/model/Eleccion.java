package com.votacion.votacionsegura.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "elecciones")
@Data
public class Eleccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "fecha_inicio")
    private String fechaInicio;

    @Column(name = "fecha_fin")
    private String fechaFin;

    @OneToMany(mappedBy = "eleccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Voto> votos;

    @OneToMany(mappedBy = "eleccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Candidato> candidatos;
}
