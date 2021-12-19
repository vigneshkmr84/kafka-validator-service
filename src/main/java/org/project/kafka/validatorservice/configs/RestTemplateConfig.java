package org.project.kafka.validatorservice.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);
    private static final int TIMEOUT_MS = 3500;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        log.info("Setting Rest Template Configuration with a timeout : " + TIMEOUT_MS);
        return builder
                .setConnectTimeout(Duration.ofMillis(TIMEOUT_MS))
                .setReadTimeout(Duration.ofMillis(TIMEOUT_MS))
                .build();
    }
}
