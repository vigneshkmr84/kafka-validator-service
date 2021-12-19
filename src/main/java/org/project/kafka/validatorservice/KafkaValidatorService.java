package org.project.kafka.validatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//(exclude = KafkaConfig.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.project.kafka.validatorservice.service", "org.project.kafka.validatorservice.controller", "org.project.kafka.validatorservice.configs"})
public class KafkaValidatorService {

    public static void main(String[] args) {
        SpringApplication.run(KafkaValidatorService.class, args);
    }

}
