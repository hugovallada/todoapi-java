package com.github.hugovallada.todoapi.service;

import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import com.github.hugovallada.todoapi.domain.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskServiceTest {

    @MockBean
    TaskRepository repository;

    @Autowired
    TaskService service;

    @Test
    public void testSave() {
        BDDMockito.given(repository.save(Mockito.any(Task.class))).willReturn(getTask());
        Task task = service.save(new Task());

        assertNotNull(task);

    }


    private Task getTask() {
        Task t = new Task();
        t.setId(1L);
        t.setDescription("Java");
        t.setName("Aprender Java");
        t.setEndDate(LocalDate.parse("2021-04-05"));
        t.setDaysLeft(30);
        t.setStatus(false);

        return t;
    }
}
