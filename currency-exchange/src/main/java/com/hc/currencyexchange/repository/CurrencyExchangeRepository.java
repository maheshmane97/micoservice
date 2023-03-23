package com.hc.currencyexchange.repository;

import com.hc.currencyexchange.domain.CurrencyExchange;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    public CurrencyExchange findByFromAndTo(String from, String To);
}
