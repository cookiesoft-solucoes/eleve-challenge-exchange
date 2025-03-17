package br.com.alysonrodrigo.elevechangecambio.infrastructure.api.service;

import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.exception.ExternalApiException;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.model.ExchangeRateResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Slf4j
@Data
public class ExchangeRateCamelService {

    @Value("${exchangerate.api.base-url}")
    private String baseUrl;

    @Value("${exchangerate.api.key}")
    private String apiKey;

    @Autowired
    private ProducerTemplate producerTemplate;

    public ExchangeRateResponse fetchRate(String from, String to) {
        try {
            ExchangeRateResponse response = producerTemplate.requestBodyAndHeaders(
                    "direct:fetchRate",
                    null,
                    Map.of(
                            "from", from,
                            "to", to,
                            "apiKey", apiKey,
                            "baseUrl", baseUrl
                    ),
                    ExchangeRateResponse.class
            );

            log.info("Fetched rate from API: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error fetching rate from API: {}", e.getMessage());
            throw new ExternalApiException("Failed to fetch rate for " + from + " to " + to);
        }
    }

}
