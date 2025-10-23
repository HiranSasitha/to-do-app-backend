package com.todo_app.CoverageX.service.impl;

import com.todo_app.CoverageX.dto.TaskDto;
import com.todo_app.CoverageX.entity.Task;
import com.todo_app.CoverageX.repository.TaskRepository;
import com.todo_app.CoverageX.service.TaskService;
import com.todo_app.CoverageX.wrappers.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<?> create(TaskDto dto) {
        try {
            Task task = new Task();
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setCreatedAt(new Date());
            task.setLastUpdatedAt(new Date());
            task.setCompleted(dto.isCompleted());

            Task savedTask = taskRepository.save(task);
            return new ResponseEntity<>(new ResponseWrapper<>().responseOk("Created"+savedTask.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseWrapper<>().responseFail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
