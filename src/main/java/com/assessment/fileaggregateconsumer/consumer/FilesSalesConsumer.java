package com.assessment.fileaggregateconsumer.consumer;

import com.assessment.fileaggregateconsumer.model.SalesFileInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class FilesSalesConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(FilesSalesConsumer.class);

    private final ObjectMapper objectMapper;

    @Value("${kafka.topics.file-aggregation}")
    private String topicName;

    @Value("${socket.destination.sales}") private String socketDestination;

    private final SimpMessagingTemplate simpleMessagingTemplate;

    @Autowired
    FilesSalesConsumer(ObjectMapper objectMapper, SimpMessagingTemplate simpleMessagingTemplate){
        this.objectMapper = objectMapper;
        this.simpleMessagingTemplate = simpleMessagingTemplate;
    }

    @KafkaListener(topics = "file-aggregation", groupId = "sales")
    public void consume(String message) {
        LOG.info("Receive a new message from topic: {}", topicName);
        try {
            LOG.info("Message context: {}", message);
            LOG.info("Sending message to client using websocket");
            SalesFileInformation salesFileInformation = objectMapper.readValue(message, SalesFileInformation.class);

            simpleMessagingTemplate.convertAndSend(socketDestination, objectMapper.writeValueAsString(salesFileInformation));
            LOG.info("Successfully sending the message to client");
        } catch (Exception e) {
            LOG.warn("Failed to read message context due to error: {}", e.getMessage());
        }
    }
}
