package ru.svanchukov.synthetic_human_core_starter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.svanchukov.synthetic_human_core_starter.dto.AndroidDTO;
import ru.svanchukov.synthetic_human_core_starter.service.AndroidService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commands")
public class AndroidController {

    private final AndroidService androidService;

    @PostMapping
    public ResponseEntity<?> createCommand(@Valid @RequestBody AndroidDTO commandDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        System.out.println("Creating command...");

        androidService.handleCommand(commandDTO);
        return ResponseEntity.ok("Команда принята");
    }



}























