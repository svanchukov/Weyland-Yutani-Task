package ru.svanchukov.synthetic_human_core_starter.handler;

public class QueueOverflowException extends Exception {

    public QueueOverflowException(String message) {
        super(message);
    }
}
