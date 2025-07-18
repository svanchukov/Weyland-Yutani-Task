package ru.svanchukov.synthetic_human_core_starter.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.svanchukov.synthetic_human_core_starter.entity.Priority;

@Getter
@Setter
public class AndroidDTO {

    @NotBlank(message = "Описание не может быть пустым")
    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    private String description;

    @NotNull(message = "Приоритет должен быть или CRITICAL или COMMON")
    private Priority priority;

    @NotBlank(message = "Автор обязателен")
    @Size(max = 100, message = "Имя автора не должно превышать 100 символов")
    private String author;

    @NotBlank(message = "Время обязательно")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2})?$",
            message = "Формат времени должен соответствовать ISO8601, например: 2025-07-16T20:00")
    private String time;

    @Override
    public String toString() {
        return "AndroidDTO{" +
                "description='" + description + '\'' +
                ", priority=" + priority +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
