package br.com.microservices.orchestrated.inventoryservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topics.orchestration}")
    private String orchestrationTopic;

    public void sendEvent(String payload) {
        try {
            kafkaTemplate.send(orchestrationTopic, payload);
            log.info("Event sent to topic {}: {}", orchestrationTopic, payload);
        } catch (Exception e) {
            log.error("Error tryping to send data to topic {}: {}", orchestrationTopic, e.getMessage());
        }
    }

}
