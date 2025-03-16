package br.com.alysonrodrigo.elevechangecambio.domain.repository;

import br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence.CurrencyConversionEntity;
import br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence.JpaCurrencyConversionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class CurrencyConversionRepositoryTest {

    @Autowired
    private JpaCurrencyConversionRepository repository;

    private CurrencyConversionEntity entity;

    @BeforeEach
    void setUp() {
        entity = new CurrencyConversionEntity();
        entity.setFromCurrency("USD");
        entity.setToCurrency("BRL");
        entity.setRate(5.25);
        entity.setConversionDate(LocalDateTime.now());
    }

    @Test
    void testSaveAndFind() {
        CurrencyConversionEntity savedEntity = repository.save(entity);
        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getId());

        CurrencyConversionEntity foundEntity = repository.findByFromCurrencyAndToCurrency("USD", "BRL");
        assertNotNull(foundEntity);
        assertEquals("USD", foundEntity.getFromCurrency());
        assertEquals("BRL", foundEntity.getToCurrency());
        assertEquals(5.25, foundEntity.getRate());
    }
}
