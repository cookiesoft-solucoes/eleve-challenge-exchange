package br.com.alysonrodrigo.elevechangecambio.domain.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
