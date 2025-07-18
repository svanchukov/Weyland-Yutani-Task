package ru.svanchukov.synthetic_human_core_starter.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "audit-topic", groupId = "audit-group-test")
    public void consume(String message) {
        try {
            log.info("-----> KafkaConsumer активен");
            log.info("Получено сообщение из Kafka: {}", message);
            // возможная логика обработки...
        } catch (Exception e) {
            log.error("Ошибка при обработке сообщения из Kafka: {}", message, e);
        }
    }



}
