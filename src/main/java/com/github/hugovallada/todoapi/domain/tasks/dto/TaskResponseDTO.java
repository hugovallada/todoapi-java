package com.github.hugovallada.todoapi.domain.tasks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @ApiModelProperty(value = "Init Date", example = "2021-04-05")
    @JsonIgnore
    private LocalDate initDate;

    @ApiModelProperty(value = "Days left", example = "31")
    private Integer daysLeft;

    @ApiModelProperty(value = "Status", example = "true")
    private boolean status;

    public Integer getDaysLeft() {
        Long days = ChronoUnit.DAYS.between(initDate, endDate);
        return days.intValue();
    }
}
