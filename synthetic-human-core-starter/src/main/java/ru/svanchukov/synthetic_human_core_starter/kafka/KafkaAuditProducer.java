package ru.svanchukov.synthetic_human_core_starter.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaAuditProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendToKafka(String topic, String message) {
        log.info("Отправка сообщения в Kafka (топик={}): {}", topic, message);
        kafkaTemplate.send(topic, "somekey", message);
    }
}
