package br.com.alysonrodrigo.elevechangecambio.application.usecase;

import br.com.alysonrodrigo.elevechangecambio.application.service.CurrencyConversionService;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.ExchangeRateClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConvertCurrencyUseCase {

    private final ExchangeRateClient exchangeRateClient;

    private final CurrencyConversionService currencyConversionService;

    public ConvertCurrencyUseCase(ExchangeRateClient exchangeRateClient,
                                  CurrencyConversionService currencyConversionService) {
        this.exchangeRateClient = exchangeRateClient;
        this.currencyConversionService = currencyConversionService;
    }

    public CurrencyConversion execute(String from, String to){
        double rate = exchangeRateClient.getRate(from, to);
        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency(from);
        conversion.setToCurrency(to);
        conversion.setRate(rate);
        conversion.setConversionDate(LocalDateTime.now());

        currencyConversionService.save(conversion);

        return conversion;
    }
}
