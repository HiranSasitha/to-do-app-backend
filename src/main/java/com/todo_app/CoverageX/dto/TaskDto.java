package com.todo_app.CoverageX.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Integer id;
    private String title;
    private String description;
    private boolean completed ;
    private String date;
}
