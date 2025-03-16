package br.com.alysonrodrigo.elevechangecambio.application.service;

import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.domain.repository.CurrencyConversionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        // Arrange
        CurrencyConversion conversion = new CurrencyConversion();
        Mockito.when(currencyConversionRepository.save(conversion)).thenReturn(conversion);
        // Act
        CurrencyConversion result = currencyConversionService.save(conversion);

        // Assert
        assertNotNull(result);
    }


}
