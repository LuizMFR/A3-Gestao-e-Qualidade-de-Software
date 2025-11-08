package com.example.Servidor.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthChecker {



    public HealthChecker(){

    }

    @GetMapping()
    public ResponseEntity<Object> HealthCheck() {
        return ResponseEntity.ok().build();
    }

}

