package com.hc.limitservice.controller;

import com.hc.limitservice.Configuration;
import com.hc.limitservice.domain.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    Configuration configuration;
    @GetMapping("/limit")
    public Limits getLimits(){
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
