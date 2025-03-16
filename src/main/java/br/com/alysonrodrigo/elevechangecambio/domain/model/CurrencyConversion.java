package br.com.alysonrodrigo.elevechangecambio.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrencyConversion {

    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private LocalDateTime conversionDate;
}
