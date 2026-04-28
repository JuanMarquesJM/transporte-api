package com.projeto.transporte_api.infrastructure;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EncomendaNotFoundException extends RuntimeException {
    public EncomendaNotFoundException(String message) {
        super(message);
    }
}
