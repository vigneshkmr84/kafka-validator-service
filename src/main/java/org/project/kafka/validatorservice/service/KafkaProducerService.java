package org.project.kafka.validatorservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * @param key       Kafka Key
     * @param message   Message (Value)
     * @param topicName Topic Name to insert messages to
     */
    public void sendMessage(String key, String message, String topicName) {
        log.info(String.format("Message will be inserted into Topic - %s", topicName));
        log.info(String.format("Message sent -> %s", message));

        ListenableFuture<SendResult<String, String>> result = this.kafkaTemplate.send(topicName, key, message);


    }
}