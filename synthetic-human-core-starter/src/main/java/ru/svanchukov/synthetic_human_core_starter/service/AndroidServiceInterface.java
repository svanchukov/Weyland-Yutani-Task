package ru.svanchukov.synthetic_human_core_starter.service;

import ru.svanchukov.synthetic_human_core_starter.dto.AndroidDTO;
import ru.svanchukov.synthetic_human_core_starter.handler.QueueOverflowException;

public interface AndroidServiceInterface {

    void handleCommand(AndroidDTO androidDTO) throws QueueOverflowException;
}
