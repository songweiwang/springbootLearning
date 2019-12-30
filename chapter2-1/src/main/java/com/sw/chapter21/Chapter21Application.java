package com.sw.chapter21;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
public class Chapter21Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter21Application.class, args);
    }

}
