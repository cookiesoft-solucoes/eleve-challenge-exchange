package br.com.alysonrodrigo.elevechangecambio.infrastructure.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {

    private String base_code;
    private String target_code;
    private double conversion_rate;
}
