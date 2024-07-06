package com.votacion.votacionsegura.service;

import com.votacion.votacionsegura.model.Usuario;
import com.votacion.votacionsegura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    public Optional<Usuario> authenticate(String dni, String contraseña) {
        Optional<Usuario> usuario = usuarioRepository.findByDni(dni);
        if (usuario.isPresent() && usuario.get().getContraseña().equals(contraseña)) {
            return usuario;
        }
        return Optional.empty();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para obtener un usuario por ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
}
