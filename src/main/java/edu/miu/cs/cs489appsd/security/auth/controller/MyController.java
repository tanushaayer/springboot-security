package edu.miu.cs.cs489appsd.security.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home")
public class MyController {

    @GetMapping
    public String home() {
        return "Spring Security home page";
    }
}
