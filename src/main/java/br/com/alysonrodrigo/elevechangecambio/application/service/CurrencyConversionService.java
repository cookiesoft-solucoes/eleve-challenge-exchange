package br.com.alysonrodrigo.elevechangecambio.application.service;

import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.domain.repository.CurrencyConversionRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    private final CurrencyConversionRepository currencyConversionRepository;

    public CurrencyConversionService(CurrencyConversionRepository currencyConversionRepository) {
        this.currencyConversionRepository = currencyConversionRepository;
    }

    public CurrencyConversion save(CurrencyConversion conversion) {
        return currencyConversionRepository.save(conversion);
    }
}
