package com.example.springboot.controller;

import com.example.springboot.services.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manoel Campos
 */
@RestController
public class HelloController {
    private final MessageService service;

    public HelloController(@Qualifier("v2") MessageService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello(){
        return service.say("Hello World!");
    }
}
