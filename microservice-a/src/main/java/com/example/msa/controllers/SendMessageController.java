package com.example.msa.controllers;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/send")
    public String sendMessage() {
        producerTemplate.sendBody("direct:kafka", "Test message " + System.currentTimeMillis());
        return "ok";
    }

}
