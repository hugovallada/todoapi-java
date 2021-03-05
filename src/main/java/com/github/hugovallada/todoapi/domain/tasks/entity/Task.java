package com.github.hugovallada.todoapi.domain.tasks.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_task")
public class Task {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String username;

    @Setter(AccessLevel.NONE)
    @CreatedDate
    private LocalDate initDate;

    private LocalDate endDate;

    private boolean status = false;

    @Transient
    private int daysLeft;

}