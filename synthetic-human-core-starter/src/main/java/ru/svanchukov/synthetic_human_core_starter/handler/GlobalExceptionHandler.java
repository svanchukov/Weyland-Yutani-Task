package ru.svanchukov.synthetic_human_core_starter.handler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QueueOverflowException.class)
    public ResponseEntity<Map<String, Object>> handleQueueOverflow(QueueOverflowException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(Map.of(
                "error", "Очередь переполнена",
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst().orElse("Ошибка валидации");

        return ResponseEntity.badRequest().body(Map.of(
                "error", "Ошибка валидации",
                "message", errorMessage,
                "timestamp", LocalDateTime.now()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleEnumConversionError(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errors", List.of("Неверное значение одного из полей. Проверьте, что приоритет указан правильно (например: CRITICAL или COMMON)."));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
