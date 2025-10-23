package com.todo_app.CoverageX.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author hiran
 * @since 23/10/25
 * to-do
 */

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean completed = false;

    private Date createdAt ;
    private Date lastUpdatedAt ;
}
