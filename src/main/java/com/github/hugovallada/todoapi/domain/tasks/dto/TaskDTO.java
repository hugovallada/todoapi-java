package com.github.hugovallada.todoapi.domain.tasks.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private String name;

    private String description;

    private String username;

    private LocalDate endDate;

    private int daysLeft;

    private boolean status;


    public TaskDTO() {
    }

    public TaskDTO(String name, String description, String username, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.endDate = endDate;
    }
}
