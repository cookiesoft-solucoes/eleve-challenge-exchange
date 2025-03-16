package br.com.alysonrodrigo.elevechangecambio.infrastructure.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.Map;

@Component
public class ExchangeRateClient {

    private final RestTemplate restTemplate;

    @Value("${exchangerate.api.base-url}")
    private String baseUrl;

    @Value("${exchangerate.api.key}")
    private String apiKey;

    public ExchangeRateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getRate(String from, String to) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(baseUrl);
        URI uri = uriBuilderFactory.builder()
                .pathSegment(apiKey, "pair", from, to)
                .build();

        Map<String, Object> response = restTemplate.getForObject(uri, Map.class);

        if (response != null && response.containsKey("conversion_rate")) {
            return (double) response.get("conversion_rate");
        }

        throw new RuntimeException("Failed to retrieve exchange rate.");
    }
}
