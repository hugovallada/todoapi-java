package com.github.hugovallada.todoapi.infra.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiError {
    private int code;

    private String status;

    private LocalDateTime timestamp;

    private String message;

    private List<String> errors;

}
