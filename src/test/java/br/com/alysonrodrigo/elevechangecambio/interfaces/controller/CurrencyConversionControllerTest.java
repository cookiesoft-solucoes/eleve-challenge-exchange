package br.com.alysonrodrigo.elevechangecambio.interfaces.controller;

import br.com.alysonrodrigo.elevechangecambio.application.dto.CurrencyConversionDTO;
import br.com.alysonrodrigo.elevechangecambio.application.usecase.ConvertCurrencyUseCase;
import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.interfaces.mapper.CurrencyConversionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@WebMvcTest(CurrencyConversionController.class)
@ActiveProfiles("test")
public class CurrencyConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConvertCurrencyUseCase useCase;

    @MockitoBean
    private CurrencyConversionMapper mapper;

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "USER")
    void testConvert() throws Exception {
        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency("USD");
        conversion.setToCurrency("BRL");
        conversion.setRate(5.25);
        conversion.setConversionDate(LocalDateTime.now());

        CurrencyConversionDTO dto = new CurrencyConversionDTO();
        dto.setFromCurrency("USD");
        dto.setToCurrency("BRL");
        dto.setRate(5.25);

        when(useCase.execute("USD", "BRL")).thenReturn(conversion);

        mockMvc.perform(MockMvcRequestBuilders.get("/conversion")
                        .param("from", "USD")
                        .param("to", "BRL"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
