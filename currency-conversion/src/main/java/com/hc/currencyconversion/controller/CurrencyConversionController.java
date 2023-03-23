package com.hc.currencyconversion.controller;

import com.hc.currencyconversion.domain.CurrencyConversion;
import com.hc.currencyconversion.repository.CurrencyConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyConversionRepository conversionRepository;

    @Autowired
    private CurrencyExchangeProxy exchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        HashMap<String, String> uriVariables=new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion conversion = responseEntity.getBody();

        CurrencyConversion currencyConversion = new CurrencyConversion(conversion.getId(), from, to, conversion.getConversionMultiple(), quantity, quantity.multiply(conversion.getConversionMultiple()), conversion.getEnvironment());
        conversionRepository.save(currencyConversion);
        return currencyConversion;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        CurrencyConversion conversion = exchangeProxy.getConversion(from, to);

        CurrencyConversion currencyConversion = new CurrencyConversion(conversion.getId(), from, to, conversion.getConversionMultiple(), quantity, quantity.multiply(conversion.getConversionMultiple()), conversion.getEnvironment());
        conversionRepository.save(currencyConversion);
        return currencyConversion;
    }
}
