package com.github.hugovallada.todoapi.domain.tasks.controller;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskRequestDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskResponseDTO;
import com.github.hugovallada.todoapi.domain.tasks.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO dto) {
        TaskResponseDTO resp = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @ApiOperation(value = "Find All tasks", nickname = "taskList")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        List<TaskResponseDTO> listResp = service.findAll();
        return ResponseEntity.ok().body(listResp);
    }

    @ApiOperation(value = "Find task by id", nickname = "taskById")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        TaskResponseDTO response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Update Status of a task by id", nickname = "updateStatusById")
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateStatus(@PathVariable Long id) {
        TaskResponseDTO responseDTO = service.updateStatus(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @ApiOperation(value = "Delete task by id", nickname = "deleteTaskById")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update task by id", nickname = "updateTask")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskRequestDTO requestDTO
    ) {
        TaskResponseDTO response = service.updateTask(id, requestDTO);
        return ResponseEntity.ok().body(response);
    }

}
