package com.example.ciexample2;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port = 8080"})
public class ExampleApplicatione2eTest {

    @LocalServerPort
    private int port;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    public RestTemplate template;

    private final String url = "http://localhost:8080/api/v1";

    @Test
    public void userController_testGreet() {
        System.out.println(this.template);

        String obj = template.getForObject(this.url, String.class);
        System.out.println(obj);
    }


    @Test
    public void userController_testGetAllEndpoint() {
        var response = this.template.getForEntity(this.url + "/users", List.class);
        System.out.println(response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode(), "should be 200 Ok Response");
        var users = response.getBody();
        System.out.println(users);
        assertNotNull(users, "should not be null");

    }


    @Test
    public void userController_testAddUserEndpoint() throws JsonProcessingException {
        User user  = new User(6, "testUser");
        String jsondata = mapper.writeValueAsString(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(jsondata, headers);
        System.out.println(jsondata);
        var response = this.template.postForEntity(this.url + "/users/add", httpEntity , String.class);
        assertEquals(HttpStatus.OK,  response.getStatusCode());
    }

}
