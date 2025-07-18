package ru.svanchukov.synthetic_human_core_starter.audit;

import jakarta.persistence.Table;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeylandWatchingYou {
}
