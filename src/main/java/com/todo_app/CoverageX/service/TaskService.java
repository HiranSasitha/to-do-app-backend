package com.todo_app.CoverageX.service;

import com.todo_app.CoverageX.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    ResponseEntity<?> create(TaskDto dto);

    ResponseEntity<?> getAll(Integer page, Integer size);

    ResponseEntity<?> markTask(Integer id, Integer markId);
}
