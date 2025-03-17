package br.com.alysonrodrigo.elevechangecambio.infrastructure.camel;

import br.com.alysonrodrigo.elevechangecambio.infrastructure.api.model.ExchangeRateResponse;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateRoute extends RouteBuilder {

    @Value("${exchangerate.api.base-url}")
    private String baseUrl;

    @Value("${exchangerate.api.key}")
    private String apiKey;


    @Override
    public void configure() throws Exception {
        from("direct:fetchRate")
                .routeId("fetchExchangeRate")
                .setHeader("CamelHttpMethod", constant("GET"))
                .toD("${header.baseUrl}/${header.apiKey}/pair/${header.from}/${header.to}?bridgeEndpoint=true&disableSSL=true")
                .log("API Response: ${body}")
                .unmarshal().json(ExchangeRateResponse.class)
                .to("direct:processRate");

        from("direct:processRate")
                .process(exchange -> {
                    var body = exchange.getIn().getBody(ExchangeRateResponse.class);
                    exchange.getMessage().setBody(body);
                });
    }
}
