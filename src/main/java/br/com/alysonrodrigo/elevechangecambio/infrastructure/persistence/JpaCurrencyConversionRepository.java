package br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCurrencyConversionRepository extends JpaRepository<CurrencyConversionEntity, Long> {
    CurrencyConversionEntity findByFromCurrencyAndToCurrency(String from, String to);
}
