package br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "currency_conversion")
public class CurrencyConversionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private LocalDateTime conversionDate;
}
