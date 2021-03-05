package com.github.hugovallada.todoapi.infra.config;


import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SpringTestDataConfig {


    @EventListener
    public void onApplicationStart(ContextRefreshedEvent event) {
        Task task = new Task("Aprender Spring", "Para a zup", "hugovallada", LocalDate.parse("2020-04-05"));
    }
}