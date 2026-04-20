package br.com.microservices.orchestrated.orderservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SagaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topics.start-saga}")
    private String startSagaTopic;

    public void sendEvent(String payload) {
        try {
            kafkaTemplate.send(startSagaTopic, payload);
            log.info("Event sent to topic {}: {}", startSagaTopic, payload);
        } catch (Exception e) {
            log.error("Error tryping to send data to topic {}: {}", startSagaTopic, e.getMessage());
        }
    }

}
