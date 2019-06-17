package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExampleMonolithicApp;
import io.github.jhipster.application.service.ExampleMonolithicKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = ExampleMonolithicApp.class)
public class ExampleMonolithicKafkaResourceIT {

    @Autowired
    private ExampleMonolithicKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        ExampleMonolithicKafkaResource kafkaResource = new ExampleMonolithicKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/example-monolithic-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
