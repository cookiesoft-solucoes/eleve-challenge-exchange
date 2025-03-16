package br.com.alysonrodrigo.elevechangecambio.application.usecase;

import br.com.alysonrodrigo.elevechangecambio.application.service.CurrencyConversionService;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllConvertCurrencyUseCase {

    private final CurrencyConversionService currencyConversionService;

    public GetAllConvertCurrencyUseCase(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    public List<CurrencyConversion> execute() {
        return currencyConversionService.findAll();
    }
}
