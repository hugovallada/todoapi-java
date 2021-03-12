package com.github.hugovallada.todoapi.domain.tasks.service;

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
import java.util.Optional;
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

    public List<TaskResponseDTO> findAll() {
        List<Task> tasks = repository.findAll();

        List<TaskResponseDTO> dtos = tasks.stream().map(task -> responseMapper.toDto(task)).collect(Collectors.toList());

        return dtos;
    }

    public TaskResponseDTO findById(Long id) {
        Optional<Task> taskOptional = repository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new RuntimeException("Is empty");
        }

        return responseMapper.toDto(taskOptional.get());
    }
    
    public TaskResponseDTO updateStatus(Long id) {
        repository.updateStatusById(id);
        Optional<Task> taskOptional = repository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new RuntimeException("Is empty");
        }

        return responseMapper.toDto(taskOptional.get());
    }


}
