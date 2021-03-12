package com.github.hugovallada.todoapi.repository;

import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import com.github.hugovallada.todoapi.domain.tasks.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        Task task1 = new Task("Aprender Java", "Estudar java para o emprego", "hugovallada", LocalDate.parse("2021-05-04"));
        Task task2 = new Task("Aprender Docker", "Estudar docker para o emprego", "hugovallada", LocalDate.parse("2021-05-04"));

        repository.saveAll(Arrays.asList(task1, task2));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSave() {
        Task task = new Task("Aprender Java", "Estudar java para o emprego", "hugovallada", LocalDate.parse("2021-05-04"));

        Task savedTask = repository.save(task);

        assertNotNull(savedTask);
    }

    @Test
    public void testFindAll() {
        List<Task> tasks = repository.findAll();

        assertNotNull(tasks);
        assertEquals(tasks.get(0).getName(), "Aprender Java");
        assertEquals(tasks.get(1).getName(), "Aprender Docker");

    }

    @Test
    public void testFindById() {

        Task task = new Task("Aprender Java", "Estudar java para o emprego", "hugovallada", LocalDate.parse("2021-05-04"));
        Task savedTask = repository.save(task);
        Optional<Task> taskObj = repository.findById(savedTask.getId());

        assertTrue(taskObj.isPresent());
        assertEquals(taskObj.get().getName(), "Aprender Java");
    }

    @Test
    public void testFindByIdWithNonExistentId() {
        Optional<Task> taskObj = repository.findById(3L);
        assertFalse(taskObj.isPresent());
    }

    @Test
    public void testUpdateStatusById() {
        repository.updateStatusById(1L);
        Optional<Task> taskObj = repository.findById(1L);
        assertTrue(taskObj.isPresent());
        assertTrue(taskObj.get().isStatus());
    }

}


