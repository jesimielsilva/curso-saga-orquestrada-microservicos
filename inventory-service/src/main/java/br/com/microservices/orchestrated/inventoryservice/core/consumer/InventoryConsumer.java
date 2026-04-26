package br.com.microservices.orchestrated.inventoryservice.core.consumer;

import br.com.microservices.orchestrated.inventoryservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InventoryConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topics.inventory-success}"
    )
    public void consumerSuccessEvent(String payload) {
        try {
            log.info("Received success event: {} from inventory-success topic", payload);
            var event = jsonUtil.toEvent(payload);
            log.info(event.toString());
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage());
        }
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topics.inventory-fail}"
    )
    public void consumerFailEvent(String payload) {
        try {
            log.info("Received rollback event: {} from inventory-fail topic", payload);
            var event = jsonUtil.toEvent(payload);
            log.info(event.toString());
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage());
        }
    }

}
