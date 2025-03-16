package br.com.alysonrodrigo.elevechangecambio.infrastructure.config;

import br.com.alysonrodrigo.elevechangecambio.domain.repository.CurrencyConversionRepository;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence.CurrencyConversionRepositoryImpl;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence.JpaCurrencyConversionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrencyConversionRepository currencyConversionRepository(JpaCurrencyConversionRepository jpaRepository) {
        return new CurrencyConversionRepositoryImpl(jpaRepository);
    }
}
