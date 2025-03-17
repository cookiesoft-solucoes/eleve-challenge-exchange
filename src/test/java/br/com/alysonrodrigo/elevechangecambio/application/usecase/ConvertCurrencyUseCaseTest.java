package br.com.alysonrodrigo.elevechangecambio.application.usecase;

import br.com.alysonrodrigo.elevechangecambio.application.service.CurrencyConversionService;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.ExchangeRateClient;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.model.ExchangeRateResponse;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.service.ExchangeRateCamelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ConvertCurrencyUseCaseTest {

    @InjectMocks
    private ConvertCurrencyUseCase convertCurrencyUseCase;

    @Mock
    private ExchangeRateCamelService exchangeRateCamelService;

    @Mock
    private ExchangeRateClient exchangeRateClient;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        String from = "USD";
        String to = "BRL";
        double rate = 5.25;
        LocalDateTime now = LocalDateTime.now();

        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setBase_code(from);
        response.setTarget_code(to);
        response.setConversion_rate(rate);

        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency(from);
        conversion.setToCurrency(to);
        conversion.setRate(rate);
        conversion.setConversionDate(now);

        Mockito.when(exchangeRateCamelService.fetchRate(from, to)).thenReturn(response);
        Mockito.when(currencyConversionService.save(any(CurrencyConversion.class))).thenReturn(conversion);

        CurrencyConversion result = convertCurrencyUseCase.execute(from, to);

        assertEquals(from, result.getFromCurrency());
        assertEquals(to, result.getToCurrency());
        assertEquals(rate, result.getRate());
    }
}
