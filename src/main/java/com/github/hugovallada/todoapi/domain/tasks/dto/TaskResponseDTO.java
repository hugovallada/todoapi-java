package com.github.hugovallada.todoapi.domain.tasks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@ApiModel(value = "Task Response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO implements Serializable {

    @ApiModelProperty(value = "Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "Name", example = "Félix")
    private String username;

    @ApiModelProperty(value = "Description", example = "A task description")
    private String description;

    @ApiModelProperty(value = "Name", example = "A task name")
    private String name;

    @ApiModelProperty(value = "Turn in Date", example = "2021-12-31")
    private LocalDate endDate;

//    @ApiModelProperty(value = "Days left", example = "31")
//    private Integer daysLeft;

    @ApiModelProperty(value = "Status", example = "true")
    private boolean status;

}
