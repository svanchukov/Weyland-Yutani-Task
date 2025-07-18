package ru.svanchukov.synthetic_human_core_starter.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svanchukov.synthetic_human_core_starter.audit.WeylandWatchingYou;
import ru.svanchukov.synthetic_human_core_starter.dto.AndroidDTO;
import ru.svanchukov.synthetic_human_core_starter.entity.Priority;
import ru.svanchukov.synthetic_human_core_starter.handler.QueueOverflowException;
import ru.svanchukov.synthetic_human_core_starter.monitoring.MonitoringService;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AndroidService implements AndroidServiceInterface{

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            2,
            60L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10),
            new ThreadPoolExecutor.AbortPolicy()
    );
    private final MonitoringService monitoringService;

    private int completedTasks = 0;

    @Autowired
    @Lazy
    private AndroidService self;

    @SneakyThrows
    @Override
    public void handleCommand(AndroidDTO commandDTO)  {
        if (commandDTO.getPriority() == Priority.CRITICAL) {
            System.out.println("Adding task to queue: " + commandDTO.getDescription());
            self.executeCommand(commandDTO);
        } else {
            try {
                executor.execute(() -> self.executeCommand(commandDTO));
            } catch (RejectedExecutionException e) {
                throw new QueueOverflowException("Очередь задач переполнена");
            }
        }
        monitoringService.updateMetrics(executor.getQueue().size(), completedTasks);
    }

    @Transactional
    @WeylandWatchingYou
    public void executeCommand(AndroidDTO commandDTO) {
        System.out.println("Выполнение команды  " + commandDTO.getPriority() + " " + commandDTO.getDescription());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        completedTasks++;
    }

    @Scheduled(fixedDelay = 2000)
    public void updateMonitoring() {
        monitoringService.updateMetrics(executor.getQueue().size(), completedTasks);
    }

    public int getQueueSize() {
        return executor.getQueue().size();
    }


}















