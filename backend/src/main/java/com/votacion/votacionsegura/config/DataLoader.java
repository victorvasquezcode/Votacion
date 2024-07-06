package com.votacion.votacionsegura.config;

import com.votacion.votacionsegura.model.Candidato;
import com.votacion.votacionsegura.model.Eleccion;
import com.votacion.votacionsegura.model.Usuario;
import com.votacion.votacionsegura.repository.CandidatoRepository;
import com.votacion.votacionsegura.repository.EleccionRepository;
import com.votacion.votacionsegura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EleccionRepository eleccionRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuarios de ejemplo
        Usuario usuario1 = new Usuario();
        usuario1.setDni("72520098");
        usuario1.setNombre("Juan");
        usuario1.setApellido("Perez");
        usuario1.setRol("votante");
        usuario1.setContraseña("258963");
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setDni("12345678");
        usuario2.setNombre("Maria");
        usuario2.setApellido("Gomez");
        usuario2.setRol("admin");
        usuario2.setContraseña("258963");
        usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setDni("72520099");
        usuario3.setNombre("Rosario");
        usuario3.setApellido("Pilar");
        usuario3.setRol("votante");
        usuario3.setContraseña("258963");
        usuarioRepository.save(usuario3);

        // Crear elecciones de ejemplo
        Eleccion eleccion1 = new Eleccion();
        eleccion1.setNombre("Elección Presidencial 2024");
        eleccion1.setFechaInicio("2024-01-01");
        eleccion1.setFechaFin("2024-01-31");
        eleccionRepository.save(eleccion1);

        Eleccion eleccion2 = new Eleccion();
        eleccion2.setNombre("Elección Municipal 2024");
        eleccion2.setFechaInicio("2024-02-01");
        eleccion2.setFechaFin("2024-02-28");
        eleccionRepository.save(eleccion2);

        // Crear candidatos para Elección Presidencial 2024
        Candidato candidato1 = new Candidato();
        candidato1.setNombre("Candidato A");
        candidato1.setPartido("Partido A");
        candidato1.setEleccion(eleccion1);
        candidatoRepository.save(candidato1);

        Candidato candidato2 = new Candidato();
        candidato2.setNombre("Candidato B");
        candidato2.setPartido("Partido B");
        candidato2.setEleccion(eleccion1);
        candidatoRepository.save(candidato2);

        // Crear candidatos para Elección Municipal 2024
        Candidato candidato3 = new Candidato();
        candidato3.setNombre("Candidato C");
        candidato3.setPartido("Partido C");
        candidato3.setEleccion(eleccion2);
        candidatoRepository.save(candidato3);

        Candidato candidato4 = new Candidato();
        candidato4.setNombre("Candidato D");
        candidato4.setPartido("Partido D");
        candidato4.setEleccion(eleccion2);
        candidatoRepository.save(candidato4);
    }
}