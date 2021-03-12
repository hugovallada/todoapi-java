package com.github.hugovallada.todoapi.domain.tasks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@ApiModel(value = "Task Request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO implements Serializable {

    @ApiModelProperty(name = "Name", example = "A task name")
    private String name;

    @ApiModelProperty(name = "Description", example = "A description example")
    private String description;

    @ApiModelProperty(name = "Username", example = "Félix")
    private String username;

    @ApiModelProperty(name = "Turn in date", example = "2021-12-31")
    private LocalDate endDate;

    @ApiModelProperty(name = "Status", example = "true")
    private boolean status;

}
