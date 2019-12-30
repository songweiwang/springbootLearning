package com.sw.chapter21;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Chapter21ApplicationTests {
    @LocalServerPort
    private int port;
    private URL base;
    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    void setUp() throws Exception{
        this.base=new URL("http://localhost:"+port);
    }

    @Test
    void contextLoads() throws MalformedURLException {
        ResponseEntity<String> response=template.getForEntity(this.base.toString(),String.class);
        System.out.println(response.getBody().toString());
        Assert.assertThat(response.getBody(),equalTo("HELLO from Spring boot"));
    }


}
