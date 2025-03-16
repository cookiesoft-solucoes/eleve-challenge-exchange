package br.com.alysonrodrigo.elevechangecambio.application.usecase;

import br.com.alysonrodrigo.elevechangecambio.application.service.CurrencyConversionService;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.ExchangeRateClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertCurrencyUseCaseTest {

    @InjectMocks
    private ConvertCurrencyUseCase convertCurrencyUseCase;

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
        // Arrange
        String from = "USD";
        String to = "BRL";
        double rate = 5.25;
        LocalDateTime now = LocalDateTime.now();

        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency(from);
        conversion.setToCurrency(to);
        conversion.setRate(rate);
        conversion.setConversionDate(now);

        Mockito.when(exchangeRateClient.getRate(from, to)).thenReturn(rate);
        Mockito.when(currencyConversionService.save(conversion)).thenReturn(conversion);

        // Act
        CurrencyConversion result = convertCurrencyUseCase.execute(from, to);

        // Assert
        assertEquals(from, result.getFromCurrency());
        assertEquals(to, result.getToCurrency());
        assertEquals(rate, result.getRate());
    }
}
