package com.example.demo.controllers;

import com.example.demo.services.TestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apikek")
@Data
public class TestClassController {

    public TestService testService;

    @GetMapping("/hello")
    public String hello(){
        return testService.test().toString();
    }
}
