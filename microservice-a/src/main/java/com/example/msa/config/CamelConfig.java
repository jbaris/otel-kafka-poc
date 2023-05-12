package com.example.msa.config;

import com.example.msa.services.HelloService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelConfig extends RouteBuilder {
    @Autowired
    private HelloService helloService;

    @Override
    public void configure() throws Exception {
        from("direct:kafka")
                .to("kafka:my-topic");
        from("kafka:my-topic")
                .bean(helloService, "processMessage")
                .log("Message processed");
        from("timer://myTimer?period=15s")
                .setBody(constant("Hello, Kafka!"))
                .to("kafka:my-topic");
    }

}
