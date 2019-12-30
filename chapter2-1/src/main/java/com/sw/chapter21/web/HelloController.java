package com.sw.chapter21.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping
    public String getHello(){
        return "HELLO from Spring boot";
    }
}
