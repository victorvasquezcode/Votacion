package com.votacion.votacionsegura.runtimeexception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EleccionNotFoundException extends RuntimeException {

    public EleccionNotFoundException(Long eleccionId) {
        super("Eleccion no encontrada con ID: " + eleccionId);
    }
}
