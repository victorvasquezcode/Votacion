package com.votacion.votacionsegura.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "roles")
@Data

public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private String permisos;
}