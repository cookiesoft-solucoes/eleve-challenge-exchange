package br.com.alysonrodrigo.elevechangecambio.application.service;

import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.domain.repository.CurrencyConversionRepository;
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

public class CurrencyConversionServiceTest {

    @InjectMocks
    private CurrencyConversionService currencyConversionService;

    @Mock
    private CurrencyConversionRepository currencyConversionRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        CurrencyConversion conversion = new CurrencyConversion();
        Mockito.when(currencyConversionRepository.save(conversion)).thenReturn(conversion);
        CurrencyConversion result = currencyConversionService.save(conversion);

        assertNotNull(result);
    }

    @Test
    public void testFindAll() {
        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setToCurrency("BRL");
        conversion.setFromCurrency("USD");
        conversion.setRate(5.25);
        conversion.setConversionDate(LocalDateTime.now());
        Mockito.when(currencyConversionRepository.findAll()).thenReturn(List.of(conversion));

        List<CurrencyConversion> currencyConversions = currencyConversionService.findAll();

        assertNotNull(currencyConversions);
        assertEquals(1, currencyConversions.size());
        assertEquals("BRL", currencyConversions.get(0).getToCurrency());

    }


}
