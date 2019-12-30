package com.sw.chater11.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${src.name}")
    private String auth;

    @Value("${src.anumber}")
    private int randomNum;


    @RequestMapping("/hello")
    public String index() {
        return "hello world, spring boot!\n"+auth+"\n"+randomNum+"\n";
    }
}
