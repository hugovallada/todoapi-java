package com.github.hugovallada.todoapi.domain.tasks.service;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskRequestDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskResponseDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import com.github.hugovallada.todoapi.domain.tasks.exceptions.TaskAlreadyExistsException;
import com.github.hugovallada.todoapi.domain.tasks.exceptions.TaskNotFoundException;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskMapper;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskRequestMapper;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskResponseMapper;
import com.github.hugovallada.todoapi.domain.tasks.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
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
        checkIfDuplicateTask(taskDTO.getName());
        Task task = repository.save(requestMapper.toModel(taskDTO));
        return responseMapper.toDto(task);
    }

    public List<TaskResponseDTO> findAll() {
        List<Task> tasks = repository.findAll();

        List<TaskResponseDTO> dtos = tasks.stream().map(task -> responseMapper.toDto(task)).collect(Collectors.toList());

        return dtos;
    }

    public TaskResponseDTO findById(Long id) {
        Task task = checkIfTaskExists(id);

        return responseMapper.toDto(task);
    }

    public TaskResponseDTO updateStatus(Long id) {
        repository.updateStatusById(id);

        Task task = checkIfTaskExists(id);

        return responseMapper.toDto(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {

        Optional<Task> task = repository.findByName(taskRequestDTO.getName());

        if (task.isEmpty()) {
            throw new TaskNotFoundException(String.format("Task with code %s not found", id));
        }

        if (task.isPresent() && task.get().getId() != id) {
            throw new TaskAlreadyExistsException("There's already a task with this name");
        }

        Task toUpdateTask = task.get();

        BeanUtils.copyProperties(requestMapper.toModel(taskRequestDTO), toUpdateTask, "id");
        Task updatedTask = repository.save(toUpdateTask);

        return responseMapper.toDto(updatedTask);
    }

    private Task checkIfTaskExists(Long id) {
        Optional<Task> taskOptional = repository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException(String.format("Task with id %s not found", id));
        }

        return taskOptional.get();
    }

    private void checkIfDuplicateTask(String name) {
        Optional<Task> taskOptional = repository.findByName(name);

        if (taskOptional.isPresent()) {
            throw new TaskAlreadyExistsException(String.format("The task with name '%s' already exists", name.toUpperCase()));
        }
    }
}
