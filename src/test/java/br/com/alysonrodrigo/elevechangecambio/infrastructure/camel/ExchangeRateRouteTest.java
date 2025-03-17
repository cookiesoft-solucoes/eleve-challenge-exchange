package br.com.alysonrodrigo.elevechangecambio.infrastructure.camel;

import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.model.ExchangeRateResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
public class ExchangeRateRouteTest{

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CamelContext camelContext;

    @Test
    void testFetchRateRoute() {
        ExchangeRateResponse mockResponse = producerTemplate.requestBodyAndHeaders(
                "direct:fetchRate",
                null,
                Map.of(
                        "from", "USD",
                        "to", "BRL",
                        "apiKey", "test_api_key",
                        "baseUrl", "https://test-api.com"
                ),
                ExchangeRateResponse.class
        );

        assertNotNull(mockResponse);
        assertEquals("USD", mockResponse.getBase_code());
        assertEquals("BRL", mockResponse.getTarget_code());
    }
}
