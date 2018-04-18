package com.github.jahwag.rex.discovery.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class GreetingEndpointIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void reactive() {
        ResponseEntity<String> response = restTemplate.getForEntity("/greet/reactive", String.class);
        assertEquals("Hello, World!", response.getBody());
    }

    @Test
    public void async() {
        ResponseEntity<String> response = restTemplate.getForEntity("/greet/async", String.class);
        assertEquals("Hello, World!", response.getBody());
    }
}