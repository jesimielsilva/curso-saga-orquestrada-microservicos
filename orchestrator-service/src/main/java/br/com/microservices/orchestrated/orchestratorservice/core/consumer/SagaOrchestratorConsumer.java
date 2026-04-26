package br.com.microservices.orchestrated.orchestratorservice.core.consumer;

import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SagaOrchestratorConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topics.start-saga}"
    )
    public void consumerStartSagaEvent(String payload) {
        try {
            log.info("Received event: {} from start-saga topic", payload);
            var event = jsonUtil.toEvent(payload);
            log.info(event.toString());
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage());
        }
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topics.orchestrator}"
    )
    public void consumerOrchestrationEvent(String payload) {
        try {
            log.info("Received event: {} from orchestrator topic", payload);
            var event = jsonUtil.toEvent(payload);
            log.info(event.toString());
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage());
        }
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topics.finish-success}"
    )
    public void consumerFinishSuccessEvent(String payload) {
        try {
            log.info("Received event: {} from finish-success topic", payload);
            var event = jsonUtil.toEvent(payload);
            log.info(event.toString());
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage());
        }
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topics.finish-fail}"
    )
    public void consumerFinishFailEvent(String payload) {
        try {
            log.info("Received event: {} from finish-fail topic", payload);
            var event = jsonUtil.toEvent(payload);
            log.info(event.toString());
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage());
        }
    }
}
