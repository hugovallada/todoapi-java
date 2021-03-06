package com.github.hugovallada.todoapi.domain.tasks.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Data
public class TaskDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String username;

    private LocalDate endDate;

    @JsonIgnore //TODO: If not ignored, will make test fail
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

    public int getDaysLeft() {
        return Period.between(LocalDate.now(), endDate).getDays();
    }
}
