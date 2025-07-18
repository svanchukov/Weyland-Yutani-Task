package ru.svanchukov.synthetic_human_core_starter.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonitoringService {

    private final MeterRegistry meterRegistry;

    private volatile int queueSize;
    private volatile int completedTasks;

    @PostConstruct
    public void init() {
        meterRegistry.gauge("android.queue.size", this, MonitoringService::getQueueSize);
        meterRegistry.gauge("android.completed.tasks", this, MonitoringService::getCompletedTasks);
    }

    public void updateMetrics(int queueSize, int completedTasks) {
        this.queueSize = queueSize;
        this.completedTasks = completedTasks;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void incrementQueueSize() {
        queueSize++;
    }

    public void decrementQueueSize() {
        queueSize--;
    }

    public void incrementCompletedTasks() {
        completedTasks++;
    }

}

