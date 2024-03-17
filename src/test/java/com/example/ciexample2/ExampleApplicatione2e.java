package com.example.ciexample2;


import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port = 8080"})
public class ExampleApplicatione2e {

    @LocalServerPort
    private int port;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public RestTemplate template;

    private final String url = "http://localhost:8080/api/v1";

    @Test
    public void context() {
        System.out.println(this.template);

        String obj = template.getForObject(this.url, String.class);
        System.out.println(obj);
    }
}
