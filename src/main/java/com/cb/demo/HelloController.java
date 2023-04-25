package com.cb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    DBService dbService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {

        return dbService.getLoginDetails(loginRequest);
    }

}
