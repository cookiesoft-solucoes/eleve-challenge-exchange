package br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence;

import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.domain.repository.CurrencyConversionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
@ActiveProfiles("test")
public class CurrencyConversionRepositoryImplTest {

    @Autowired
    private JpaCurrencyConversionRepository jpaRepository;

    private CurrencyConversionRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CurrencyConversionRepositoryImpl(jpaRepository);
    }

    @Test
    void testSaveAndFind() {
        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency("USD");
        conversion.setToCurrency("BRL");
        conversion.setRate(5.25);
        conversion.setConversionDate(LocalDateTime.now());

        CurrencyConversion saved = repository.save(conversion);
        Assertions.assertNotNull(saved);
        assertEquals("USD", saved.getFromCurrency());
        assertEquals("BRL", saved.getToCurrency());
    }
}
