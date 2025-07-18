package ru.svanchukov.bishop_prototype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.svanchukov.synthetic_human_core_starter.dto.AndroidDTO;
import ru.svanchukov.synthetic_human_core_starter.service.AndroidService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/commands")
public class BishopController {

    private final AndroidService androidService;

    @PostMapping
    public String receivedCommands(@RequestBody AndroidDTO commandDTO) {
        androidService.handleCommand(commandDTO);
        return "Команда принята и будет обработана";
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Очередь задач: " + androidService.getQueueSize();
    }


}
