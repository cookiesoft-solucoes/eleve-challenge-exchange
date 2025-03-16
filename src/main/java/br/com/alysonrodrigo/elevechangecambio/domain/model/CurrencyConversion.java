package br.com.alysonrodrigo.elevechangecambio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {

    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private LocalDateTime conversionDate;


}
