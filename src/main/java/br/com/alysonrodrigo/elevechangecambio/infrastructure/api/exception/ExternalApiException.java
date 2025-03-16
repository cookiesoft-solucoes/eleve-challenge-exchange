package br.com.alysonrodrigo.elevechangecambio.infrastructure.api.exception;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message) {
        super(message);
    }
}
