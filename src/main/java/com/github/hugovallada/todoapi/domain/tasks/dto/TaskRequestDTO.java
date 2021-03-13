package com.github.hugovallada.todoapi.domain.tasks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@ApiModel(value = "Task Request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO implements Serializable {

    @ApiModelProperty(name = "Name", example = "A task name")
    @NotNull(message = "Task name can't be null")
    @Length(min = 3, max = 50, message = "The task must have a name with length between 3 and 50")
    private String name;

    @ApiModelProperty(name = "Description", example = "A description example")
    @NotNull(message = "Task description can't be null")
    @Length(min = 10, max = 200, message = "The task must have a description with length between 3 and 200")
    private String description;

    @ApiModelProperty(name = "Username", example = "Félix")
    @NotNull(message = "Username can't be null")
    @Length(min = 3, max = 10, message = "Username must have between 3 and 10")
    private String username;

    @ApiModelProperty(name = "Turn in date", example = "2021-12-31")
    @NotNull(message = "Date can't be null")
    @Future(message = "The date must be in the future")
    private LocalDate endDate;


}
