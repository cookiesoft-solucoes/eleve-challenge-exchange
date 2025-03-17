package br.com.alysonrodrigo.elevechangecambio.application.usecase;

import br.com.alysonrodrigo.elevechangecambio.application.service.CurrencyConversionService;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.ExchangeRateClient;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.exception.ExternalApiException;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.service.ExchangeRateCamelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConvertCurrencyUseCase {

    private final ExchangeRateClient exchangeRateClient;

    private final CurrencyConversionService currencyConversionService;
    private final ExchangeRateCamelService exchangeRateCamelService;

    public ConvertCurrencyUseCase(ExchangeRateClient exchangeRateClient,
                                  CurrencyConversionService currencyConversionService,
                                  ExchangeRateCamelService exchangeRateCamelService) {
        this.exchangeRateClient = exchangeRateClient;
        this.currencyConversionService = currencyConversionService;
        this.exchangeRateCamelService = exchangeRateCamelService;
    }

    public CurrencyConversion execute(String from, String to){
        var response = exchangeRateCamelService.fetchRate(from, to);
        if (response == null || response.getConversion_rate() == 0) {
            throw new ExternalApiException("Failed to retrieve conversion rate for " + from + " to " + to);
        }

        var conversion = new CurrencyConversion();
        conversion.setFromCurrency(from);
        conversion.setToCurrency(to);
        conversion.setRate(response.getConversion_rate());
        conversion.setConversionDate(LocalDateTime.now());

        currencyConversionService.save(conversion);

        return conversion;
    }
}
