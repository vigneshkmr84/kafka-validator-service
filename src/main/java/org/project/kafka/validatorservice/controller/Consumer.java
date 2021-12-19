package org.project.kafka.validatorservice.controller;

import org.project.kafka.validatorservice.model.Order;
import org.project.kafka.validatorservice.service.KafkaProducerService;
import org.project.kafka.validatorservice.utils.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    JsonParser jsonParser = new JsonParser();

    @Autowired
    Environment environment;

    @Autowired
    KafkaProducerService producerService;

    @Autowired
    ApplicationContext context;

    private final static String[] errorMessages = { "", "Invalid Address Field", "Error in validating Phone number", "Error in connecting to database", "Request Timeout", "Internal Server Error"};

    @KafkaListener(topics = "incoming-orders", groupId = "demo-cluster")
    public void consume(@Payload String message
                        , @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long receivedTimeStamp)
                        throws InterruptedException {

        //System.out.println("Received Message Key : " + key);
        System.out.println("Received Message timestamp : " + receivedTimeStamp);

        log.info("Incoming Message - " + message);
        Order incomingOrder = jsonParser.parse(message);


        System.out.println("Incoming message : " + incomingOrder.toString());

        // need to insert the messages to redis service
        // Put the message to Next Service Topic

        String redisServiceBaseURL = environment.getProperty("redis.service.base-url") + "/status";
        String validatedOrderKafkaTopic = environment.getProperty("kafka.producer-topic");
        String serviceName = environment.getProperty("service.name");
        RestTemplate restTemplate = context.getBean("restTemplate", RestTemplate.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // for Observability testing (Distributed Tracing
        if ( incomingOrder.getId() % 13 == 0 ){
            int sleepTime = getSleepTime();
            log.info("Sleep Enabled for : " + sleepTime);
            Thread.sleep(sleepTime);
        }

        log.info("Final URL : " + redisServiceBaseURL);
        if (isValid(incomingOrder)) {
            // move the message to next kafka topic
            // and make an insert to redis service
            producerService.sendMessage(incomingOrder.getOrderId(), jsonParser.objectToString(incomingOrder), validatedOrderKafkaTopic);

            Map<String, String> map = new HashMap<>();
            map.put("status", "success");
            map.put("service", serviceName);
            map.put("id", incomingOrder.getOrderId());
            map.put("error", "");
            HttpEntity<String> entity = new HttpEntity<>(jsonParser.mapToString(map), headers);

            try {
                ResponseEntity<String> response = restTemplate.exchange(redisServiceBaseURL, HttpMethod.POST, entity, String.class);
                log.info("HTTP Status Code : " + response.getStatusCode());
                log.info("HTTP Message : " + response.getBody());
                log.info("Message successfully added to Redis");
            } catch (Exception e) {
                log.error("TimeOut occurred during Redis insert " + e.getMessage());

            }
        } else {
            // Make an Error entry to Redis service

            Map<String, String> map = new HashMap<>();
            map.put("status", "error");
            map.put("service", serviceName);
            map.put("id", incomingOrder.getOrderId());
            // will give an Error code randomly - can be interesting to observe this part
            map.put("error", errorMessages[new Random().nextInt(errorMessages.length)]);

            HttpEntity<String> entity = new HttpEntity<>(jsonParser.mapToString(map), headers);

            ResponseEntity<String> response = restTemplate.exchange(redisServiceBaseURL, HttpMethod.POST, entity, String.class);

            log.info("HTTP Status Code : " + response.getStatusCode());
            log.info("HTTP Message : " + response.getBody());

        }
    }


    // Simple Error generator fn - that will return false
    // if the Order-Id is a multiple of 7
    public boolean isValid(Order order) {
        return !(order.getId() % 7 == 0);
    }

    public int getSleepTime() {
        int min=1000;
        int max = 1900;
        return (int) ((Math.random() * (max - min)) + min);
    }

}
