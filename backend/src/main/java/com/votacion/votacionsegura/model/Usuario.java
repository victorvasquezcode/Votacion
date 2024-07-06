package com.votacion.votacionsegura.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    private String nombre;

    private String apellido;

    @Column(name = "rol")
    private String rol; // "votante" o "admin"

    private String contraseña;
}