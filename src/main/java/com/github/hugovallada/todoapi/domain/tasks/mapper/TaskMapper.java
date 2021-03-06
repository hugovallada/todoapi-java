package com.github.hugovallada.todoapi.domain.tasks.mapper;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "endDate", target = "endDate")
        // fazer a conversão de datas
    Task toModel(TaskDTO dto);

    TaskDTO toDTO(Task task);
}
