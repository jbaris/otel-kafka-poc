package com.example.msa.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Value("${msb.url:http://localhost::8080}")
    private String urlBase;

    public void processMessage(String message) {
        logger.info("Message received: {}", message);
        RestTemplate restTemplate = new RestTemplate();
        String url = urlBase + "/hello";
        String response = restTemplate.getForObject(url, String.class);
        logger.info("Response from microservice-b: {}", response);
    }

}
