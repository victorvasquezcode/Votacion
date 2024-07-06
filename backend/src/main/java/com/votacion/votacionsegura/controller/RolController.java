package com.votacion.votacionsegura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.votacion.votacionsegura.model.Rol;
import com.votacion.votacionsegura.repository.RolRepository;
import com.votacion.votacionsegura.runtimeexception.RolNotFoundException;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;


    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol savedRol = rolRepository.save(rol);
        try {
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RolNotFoundException(id));
        return ResponseEntity.ok(rol);
    }

    @GetMapping
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Long id, @RequestBody Rol rol) {
        Rol existingRol = rolRepository.findById(id)
                .orElseThrow(() -> new RolNotFoundException(id));

        existingRol.setNombre(rol.getNombre());
        existingRol.setPermisos(rol.getPermisos());
        Rol updatedRol = rolRepository.save(existingRol);
        return ResponseEntity.ok(updatedRol);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRol(@PathVariable Long id) {
        rolRepository.deleteById(id);
    }
}