package br.com.fiap.soat1.t32.exceptions;

public class DuplicateKeyException extends RuntimeException{

    public DuplicateKeyException(String message) {
        super(message);
    }

}
