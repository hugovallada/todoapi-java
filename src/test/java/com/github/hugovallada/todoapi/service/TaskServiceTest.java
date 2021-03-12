package com.github.hugovallada.todoapi.service;

import com.github.hugovallada.todoapi.domain.tasks.dto.TaskDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskRequestDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskResponseDTO;
import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskMapper;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskRequestMapper;
import com.github.hugovallada.todoapi.domain.tasks.mapper.TaskResponseMapper;
import com.github.hugovallada.todoapi.domain.tasks.repository.TaskRepository;
import com.github.hugovallada.todoapi.domain.tasks.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskServiceTest {

    @MockBean
    TaskRepository repository;

    @Autowired
    TaskService service;

    @Autowired
    TaskMapper mapper;

    @Autowired
    TaskResponseMapper responseMapper;

    @Autowired
    TaskRequestMapper requestMapper;


    //TODO: Tests are failling

    @Test
    public void testSave() {
        BDDMockito.given(repository.save(Mockito.any(Task.class))).willReturn(requestMapper.toModel(getTaskRequest()));

        TaskResponseDTO task = service.save(getTaskRequest());
        assertNotNull(task);

        assertEquals(task.getName(), "Aprender Java");
        assertEquals(task.getId(), 1L);
    }

    @Test
    public void testFindAll() {

        List<Task> tasks = new ArrayList<>();
        tasks.add(responseMapper.toModel(getTask()));

        BDDMockito.given(repository.findAll()).willReturn(tasks);

        List<TaskDTO> dtos = service.findAll();

        assertNotNull(dtos);
        assertEquals(dtos.get(0).getName(), "Aprender Java");

    }


    private TaskResponseDTO getTask() {
        TaskResponseDTO t = new TaskResponseDTO();
        t.setId(1L);
        t.setDescription("Java");
        t.setName("Aprender Java");
        t.setEndDate(LocalDate.parse("2021-04-05"));
        t.setUsername("hugovallada");
//        t.setDaysLeft(30);
        t.setStatus(false);

        return t;
    }

    private TaskRequestDTO getTaskRequest() {
        TaskRequestDTO t = new TaskRequestDTO();
        t.setDescription("Java");
        t.setName("Aprender Java");
        t.setEndDate(LocalDate.parse("2021-04-05"));
        t.setUsername("hugovallada");
        t.setStatus(false);

        return t;
    }
}
