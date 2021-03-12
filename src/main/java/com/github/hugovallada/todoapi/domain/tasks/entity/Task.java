package com.github.hugovallada.todoapi.domain.tasks.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_task")
public class Task implements Serializable {

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

    public Task(String name, String description, String username, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.endDate = endDate;
    }


}