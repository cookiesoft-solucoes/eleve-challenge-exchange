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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetAllConvertCurrencyUseCaseTest {

    @InjectMocks
    private GetAllConvertCurrencyUseCase getAllConvertCurrencyUseCase;

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

        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency(from);
        conversion.setToCurrency(to);
        conversion.setRate(rate);
        conversion.setConversionDate(now);

        Mockito.when(currencyConversionService.findAll()).thenReturn(Arrays.asList(conversion));

        List<CurrencyConversion> result = getAllConvertCurrencyUseCase.execute();

        assertNotNull(result);
        assertEquals(from, result.get(0).getFromCurrency());
        assertEquals(to, result.get(0).getToCurrency());
        assertEquals(rate, result.get(0).getRate());
    }
}
