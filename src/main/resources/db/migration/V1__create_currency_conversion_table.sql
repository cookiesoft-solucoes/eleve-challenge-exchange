-- Flyway Migration Script: V1__create_currency_conversion_table.sql
CREATE TABLE currency_conversion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    from_currency VARCHAR(3) NOT NULL,
    to_currency VARCHAR(3) NOT NULL,
    rate DOUBLE NOT NULL,
    conversion_date TIMESTAMP NOT NULL,
    UNIQUE (from_currency, to_currency, conversion_date)
);

CREATE INDEX idx_currency_conversion_from_to ON currency_conversion (from_currency, to_currency);