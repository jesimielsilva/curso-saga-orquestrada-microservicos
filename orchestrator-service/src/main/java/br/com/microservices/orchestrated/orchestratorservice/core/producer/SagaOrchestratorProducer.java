package br.com.microservices.orchestrated.orchestratorservice.core.producer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SagaOrchestratorProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String payload, String topic) {
        try {
            kafkaTemplate.send(topic, payload);
            log.info("Event sent to topic {}: {}", topic, payload);
        } catch (Exception e) {
            log.error("Error tryping to send data to topic {}: {}", topic, e.getMessage());
        }
    }

}
