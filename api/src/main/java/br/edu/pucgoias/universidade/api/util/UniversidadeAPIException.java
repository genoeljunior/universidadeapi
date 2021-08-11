package br.edu.pucgoias.universidade.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UniversidadeAPIException extends ResponseStatusException {
    public UniversidadeAPIException(String mensagem){
        super(HttpStatus.INTERNAL_SERVER_ERROR, mensagem);
    }
}
