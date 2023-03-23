package com.hc.currencyexchange.controller;

import com.hc.currencyexchange.domain.CurrencyExchange;
import com.hc.currencyexchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository exchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchange(@PathVariable String from, @PathVariable String to){
        CurrencyExchange exchange=exchangeRepository.findByFromAndTo(from, to);
        String port = environment.getProperty("local.server.port");
        exchange.setEnvironment(port);
        return exchange;
    }

        @PostMapping("/exchange")
    public CurrencyExchange save(@RequestBody CurrencyExchange currencyExchange){
        CurrencyExchange exchange = exchangeRepository.save(currencyExchange);
        return exchange;
    }

}
