package com.github.hugovallada.todoapi.domain.tasks.mapper;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskResponseDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {
    TaskResponseMapper INSTANCE = Mappers.getMapper(TaskResponseMapper.class);

    Task toModel(TaskResponseDTO dto);

    @Mapping(source = "endDate", target = "endDate")
    TaskResponseDTO toDto(Task task);


}
