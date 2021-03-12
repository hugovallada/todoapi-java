package com.github.hugovallada.todoapi.domain.tasks.mapper;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskRequestDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskRequestMapper {

    TaskRequestMapper INSTANCE = Mappers.getMapper(TaskRequestMapper.class);

    @Mapping(source = "endDate", target = "endDate")
    Task toModel(TaskRequestDTO dto);

    TaskRequestDTO toDto(Task task);
}
