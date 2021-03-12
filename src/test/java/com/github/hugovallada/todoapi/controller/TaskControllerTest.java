package com.github.hugovallada.todoapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskRequestDTO;
import com.github.hugovallada.todoapi.domain.tasks.dto.TaskResponseDTO;
import com.github.hugovallada.todoapi.domain.tasks.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {


    @MockBean
    TaskService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void testCreate() throws Exception {
        BDDMockito.given(service.save(Mockito.any(TaskRequestDTO.class))).willReturn(getMockTask());
        mvc.perform(MockMvcRequestBuilders.post("/task")
                .content(getJsonPayload(1L, "Java", "Aprender Java", LocalDate.parse("2021-04-05"), "hugovallada"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Java"));
    }

    private TaskResponseDTO getMockTask() {
        TaskResponseDTO task = new TaskResponseDTO();
        task.setId(1L);
        task.setName("Java");
        task.setDescription("Aprender Java");
        task.setEndDate(LocalDate.parse("2021-04-05"));
        task.setUsername("hugovallada");
//        task.setDaysLeft(30);

        return task;
    }

    private String getJsonPayload(Long id, String name, String description, LocalDate endDate, String username) throws JsonProcessingException {
        TaskDTO dto = new TaskDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
//        dto.setEndDate(endDate);
        dto.setUsername(username);


        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }


}
