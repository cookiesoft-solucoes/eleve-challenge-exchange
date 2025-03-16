package br.com.alysonrodrigo.elevechangecambio.domain.repository;

import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;

import java.util.List;

public interface CurrencyConversionRepository {

    CurrencyConversion save(CurrencyConversion conversion);
    List<CurrencyConversion> findAll();
    CurrencyConversion findByFromCurrencyAndToCurrency(String from, String to);
}
