package br.com.alysonrodrigo.elevechangecambio.infrastructure.persistence;

import br.com.alysonrodrigo.elevechangecambio.domain.model.CurrencyConversion;
import br.com.alysonrodrigo.elevechangecambio.domain.repository.CurrencyConversionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyConversionRepositoryImpl implements CurrencyConversionRepository {

    private final JpaCurrencyConversionRepository jpaRepository;

    public CurrencyConversionRepositoryImpl(JpaCurrencyConversionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public CurrencyConversion save(CurrencyConversion conversion) {
        CurrencyConversionEntity entity = new CurrencyConversionEntity();
        entity.setFromCurrency(conversion.getFromCurrency());
        entity.setToCurrency(conversion.getToCurrency());
        entity.setRate(conversion.getRate());
        entity.setConversionDate(conversion.getConversionDate());

        CurrencyConversionEntity savedEntity = jpaRepository.save(entity);

        conversion.setConversionDate(savedEntity.getConversionDate());
        return conversion;
    }

    @Override
    public List<CurrencyConversion> findAll() {
        return jpaRepository.findAll().stream().map(entity -> {
            CurrencyConversion conversion = new CurrencyConversion();
            conversion.setFromCurrency(entity.getFromCurrency());
            conversion.setToCurrency(entity.getToCurrency());
            conversion.setRate(entity.getRate());
            conversion.setConversionDate(entity.getConversionDate());
            return conversion;
        }).collect(Collectors.toList());
    }

    @Override
    public CurrencyConversion findByFromCurrencyAndToCurrency(String from, String to) {
        CurrencyConversionEntity entity = jpaRepository.findByFromCurrencyAndToCurrency(from, to);
        if (entity == null) return null;

        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency(entity.getFromCurrency());
        conversion.setToCurrency(entity.getToCurrency());
        conversion.setRate(entity.getRate());
        conversion.setConversionDate(entity.getConversionDate());
        return conversion;
    }
}
