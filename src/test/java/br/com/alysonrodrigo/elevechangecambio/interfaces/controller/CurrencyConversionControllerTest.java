package br.com.alysonrodrigo.elevechangecambio.interfaces.controller;

import br.com.alysonrodrigo.elevechangecambio.application.dto.CurrencyConversionDTO;
import br.com.alysonrodrigo.elevechangecambio.application.usecase.ConvertCurrencyUseCase;
import br.com.alysonrodrigo.elevechangecambio.application.usecase.GetAllConvertCurrencyUseCase;
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
import java.util.List;

import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyConversionController.class)
@ActiveProfiles("test")
public class CurrencyConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConvertCurrencyUseCase useCase;

    @MockitoBean
    private GetAllConvertCurrencyUseCase getAllConvertCurrencyUseCase;

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

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "USER")
    void testGetAllConversions() throws Exception {
        CurrencyConversion conversion1 = new CurrencyConversion("USD", "BRL", 5.2, null);
        CurrencyConversion conversion2 = new CurrencyConversion("EUR", "USD", 1.1, null);

        when(getAllConvertCurrencyUseCase.execute()).thenReturn(List.of(conversion1, conversion2));

        mockMvc.perform(get("/conversion/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].fromCurrency").value("USD"))
                .andExpect(jsonPath("$[0].toCurrency").value("BRL"))
                .andExpect(jsonPath("$[1].fromCurrency").value("EUR"))
                .andExpect(jsonPath("$[1].toCurrency").value("USD"));
    }
}
