package br.com.alysonrodrigo.elevechangecambio.infrastructure.api.service;

import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.exception.ExternalApiException;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.model.ExchangeRateResponse;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ExchangeRateCamelServiceTest {

    @InjectMocks
    private ExchangeRateCamelService exchangeRateCamelService;

    @Mock
    private ProducerTemplate producerTemplate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        exchangeRateCamelService.setApiKey("test_api_key");
        exchangeRateCamelService.setBaseUrl("http://test-api.com");
    }

    @Test
    void testFetchRateSuccess() {
        String from = "USD";
        String to = "BRL";
        double rate = 5.25;

        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setBase_code(from);
        mockResponse.setTarget_code(to);
        mockResponse.setConversion_rate(rate);

        Mockito.when(producerTemplate.requestBodyAndHeaders(
                any(String.class),
                any(),
                any(Map.class),
                Mockito.eq(ExchangeRateResponse.class))
        ).thenReturn(mockResponse);

        ExchangeRateResponse response = exchangeRateCamelService.fetchRate(from, to);

        assertNotNull(response);
        assertEquals(from, response.getBase_code());
        assertEquals(to, response.getTarget_code());
        assertEquals(rate, response.getConversion_rate());
    }

    @Test
    void testFetchRateFailure() {
        String from = "USD";
        String to = "BRL";

        Mockito.when(producerTemplate.requestBodyAndHeaders(
                any(String.class),
                any(),
                any(Map.class),
                Mockito.eq(ExchangeRateResponse.class))
        ).thenThrow(new RuntimeException("API error"));

        Exception exception = assertThrows(ExternalApiException.class, () -> {
            exchangeRateCamelService.fetchRate(from, to);
        });

        String expectedMessage = "Failed to fetch rate for USD to BRL";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}
