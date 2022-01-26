package com.azure.cdc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @GetMapping("/whoami")
    public String getGreeting() {
        return "CDC Consumer Testing";
    }
}
