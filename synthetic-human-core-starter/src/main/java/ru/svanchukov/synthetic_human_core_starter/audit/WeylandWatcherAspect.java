package ru.svanchukov.synthetic_human_core_starter.audit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.svanchukov.synthetic_human_core_starter.kafka.KafkaAuditProducer;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class WeylandWatcherAspect {

    private final KafkaAuditProducer kafkaAuditProducer;

    @Value("${audit.mode:console}")
    private String auditMode;

    @Before("@annotation(ru.svanchukov.synthetic_human_core_starter.audit.WeylandWatchingYou)")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        String logMessage = String.format("[WeylandWatchingYou] %s.%s вызван в %s с параметрами: %s",
                className,
                methodName,
                LocalDateTime.now(),
                Arrays.toString(args)
        );

        if ("kafka".equalsIgnoreCase(auditMode)) {
            kafkaAuditProducer.sendToKafka("audit-topic", logMessage);
        } else {
            log.info(logMessage);
        }
    }
}
