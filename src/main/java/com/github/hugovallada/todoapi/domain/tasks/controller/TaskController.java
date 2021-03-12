package com.github.hugovallada.todoapi.domain.tasks.controller;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskRequestDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskResponseDTO;
import com.github.hugovallada.todoapi.domain.tasks.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Task")
@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create new task", nickname = "taskCreation")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody TaskRequestDTO dto) {
        TaskResponseDTO resp = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
