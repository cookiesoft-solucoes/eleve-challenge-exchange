package br.com.alysonrodrigo.elevechangecambio.interfaces.mapper;

import br.com.alysonrodrigo.elevechangecambio.application.dto.CurrencyConversionDTO;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyConversionMapperTest {

    @Test
    void testToDTO() {
        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency("USD");
        conversion.setToCurrency("BRL");
        conversion.setRate(5.25);
        conversion.setConversionDate(LocalDateTime.now());

        CurrencyConversionDTO dto = CurrencyConversionMapper.toDTO(conversion);

        assertEquals("USD", dto.getFromCurrency());
        assertEquals("BRL", dto.getToCurrency());
        assertEquals(5.25, dto.getRate());
    }
}
