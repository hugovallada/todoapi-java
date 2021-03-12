package com.github.hugovallada.todoapi.domain.tasks.mapper;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toModel(TaskDTO dto);

    //@Mapping(source = "endDate", target = "endDate")
    TaskDTO toDTO(Task task);

}
