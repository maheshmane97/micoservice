package com.hc.currencyconversion.repository;

import com.hc.currencyconversion.domain.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion,Long> {

    public CurrencyConversion findByFromAndTo(String from, String to);
}
