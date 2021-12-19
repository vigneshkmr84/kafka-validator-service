package org.project.kafka.validatorservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    private static final Logger log = LoggerFactory.getLogger(HealthCheck.class);

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        log.info("Health OK");
        return ResponseEntity.ok().body("OK");
    }
}
