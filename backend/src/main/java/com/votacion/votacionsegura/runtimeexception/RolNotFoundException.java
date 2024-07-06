package com.votacion.votacionsegura.runtimeexception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RolNotFoundException extends RuntimeException {

    public RolNotFoundException(Long rolId) {
        super("Rol no encontrado con ID: " + rolId);
    }
}