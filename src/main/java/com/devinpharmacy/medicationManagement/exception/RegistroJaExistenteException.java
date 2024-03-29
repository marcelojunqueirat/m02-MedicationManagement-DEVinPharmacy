package com.devinpharmacy.medicationManagement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RegistroJaExistenteException extends RuntimeException {
    private String nome;
    private String identificador;

    public RegistroJaExistenteException(String nome, Integer identificador) {
        this(nome, identificador.toString());
    }

    public RegistroJaExistenteException(String nome, Long identificador) {
        this(nome, identificador.toString());
    }

    public String getMessage() {
        if (nome == null || identificador == null)
            return null;
        return String.format("Registro(s) '%s' já cadastrado(s) com identificador(es) '%s'", nome, identificador);
    }
}
