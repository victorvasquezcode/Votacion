package com.votacion.votacionsegura.runtimeexception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VotoNotFoundException extends RuntimeException {

    public VotoNotFoundException(Long votoId) {
        super("Voto no encontrado con ID: " + votoId);
    }
}