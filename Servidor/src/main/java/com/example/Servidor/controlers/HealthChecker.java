package com.example.servidor.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HealthChecker {



    public HealthChecker(){

    }

    @GetMapping()
    public ResponseEntity<Object> HealthCheck() {
        return ResponseEntity.ok().build();
    }

}

