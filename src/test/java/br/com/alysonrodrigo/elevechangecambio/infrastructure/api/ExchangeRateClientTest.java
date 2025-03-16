package br.com.alysonrodrigo.elevechangecambio.infrastructure.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class ExchangeRateClientTest {

    @Autowired
    private ExchangeRateClient exchangeRateClient;

    @Container
    static GenericContainer<?> mockApiContainer = new GenericContainer<>("wiremock/wiremock:2.35.0")
            .withExposedPorts(8080)
            .waitingFor(Wait.forHttp("/9a9bbb26e7401d67e276fdf2/pair/USD/BRL").forStatusCode(200))
            .waitingFor(Wait.forListeningPort());

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        String baseUrl = String.format("http://localhost:%d", mockApiContainer.getMappedPort(8080));
        registry.add("exchangerate.api.base-url", () -> baseUrl);
    }

    @Test
    void testGetRate() {

        WireMock.configureFor("localhost", mockApiContainer.getMappedPort(8080));

        stubFor(get(urlEqualTo("/9a9bbb26e7401d67e276fdf2/pair/USD/BRL"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"conversion_rate\":5.25}")));

        double rate = exchangeRateClient.getRate("USD", "BRL");

        assertEquals(5.25, rate, "A taxa de câmbio não está correta.");
    }
}
