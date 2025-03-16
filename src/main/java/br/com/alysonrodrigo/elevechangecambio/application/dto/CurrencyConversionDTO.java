package br.com.alysonrodrigo.elevechangecambio.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrencyConversionDTO {

    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private LocalDateTime conversionDate;
}
