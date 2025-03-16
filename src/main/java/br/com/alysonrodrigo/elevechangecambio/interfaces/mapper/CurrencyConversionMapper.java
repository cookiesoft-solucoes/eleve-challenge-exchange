package br.com.alysonrodrigo.elevechangecambio.interfaces.mapper;

import br.com.alysonrodrigo.elevechangecambio.application.dto.CurrencyConversionDTO;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;

public class CurrencyConversionMapper {

    public static CurrencyConversionDTO toDTO(CurrencyConversion conversion) {
        CurrencyConversionDTO dto = new CurrencyConversionDTO();
        dto.setFromCurrency(conversion.getFromCurrency());
        dto.setToCurrency(conversion.getToCurrency());
        dto.setRate(conversion.getRate());
        dto.setConversionDate(conversion.getConversionDate());
        return dto;
    }
}
