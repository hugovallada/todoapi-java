package com.github.hugovallada.todoapi.domain.tasks.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@ApiModel("Task Data Transfer Object")
@Data
public class TaskDTO implements Serializable {

    @ApiModelProperty(name = "Id")
    private Long id;

    @ApiModelProperty(name = "Name")
    private String name;

    @ApiModelProperty(name = "Description")
    private String description;

    @ApiModelProperty(name = "Username")
    private String username;

    @ApiModelProperty(name = "Turn in date")
    private LocalDate endDate;

    @JsonIgnore //TODO: If not ignored, will make test fail
    private int daysLeft;

    @ApiModelProperty(name = "Status")
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
