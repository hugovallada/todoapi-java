package com.github.hugovallada.todoapi.domain.tasks.service;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskRequestDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskResponseDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskMapper;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskRequestMapper;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskResponseMapper;
import com.github.hugovallada.todoapi.domain.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {


    private TaskRepository repository;

    private TaskMapper mapper;

    private TaskRequestMapper requestMapper;

    private TaskResponseMapper responseMapper;

    @Autowired
    public TaskService(TaskRepository repository, TaskMapper mapper, TaskRequestMapper requestMapper, TaskResponseMapper responseMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    public TaskResponseDTO save(TaskRequestDTO taskDTO) {
        Task task = repository.save(requestMapper.toModel(taskDTO));
        return responseMapper.toDto(task);
    }

    public List<TaskDTO> findAll() {
        List<Task> tasks = repository.findAll();

        List<TaskDTO> dtos = tasks.stream().map(task -> mapper.toDTO(task)).collect(Collectors.toList());

        return dtos;
    }
}
