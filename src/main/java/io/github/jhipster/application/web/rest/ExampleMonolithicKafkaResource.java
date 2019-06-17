package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ExampleMonolithicKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/example-monolithic-kafka")
public class ExampleMonolithicKafkaResource {

    private final Logger log = LoggerFactory.getLogger(ExampleMonolithicKafkaResource.class);

    private ExampleMonolithicKafkaProducer kafkaProducer;

    public ExampleMonolithicKafkaResource(ExampleMonolithicKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
