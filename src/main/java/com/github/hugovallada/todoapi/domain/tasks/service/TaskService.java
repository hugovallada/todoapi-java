package com.github.hugovallada.todoapi.domain.tasks.service;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskMapper;
import com.github.hugovallada.todoapi.domain.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {


    private TaskRepository repository;

    private TaskMapper mapper;

    @Autowired
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TaskDTO save(TaskDTO taskDTO) {

        Task task = repository.save(mapper.toModel(taskDTO));

        return mapper.toDTO(task);
    }

    public List<TaskDTO> findAll() {
        List<Task> tasks = repository.findAll();

        List<TaskDTO> dtos = tasks.stream().map(task -> mapper.toDTO(task)).collect(Collectors.toList());
        
        return dtos;
    }
}
