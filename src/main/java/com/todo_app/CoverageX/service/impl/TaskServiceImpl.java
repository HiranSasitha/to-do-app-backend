package com.todo_app.CoverageX.service.impl;

import com.todo_app.CoverageX.dto.TaskDto;
import com.todo_app.CoverageX.entity.Task;
import com.todo_app.CoverageX.repository.TaskRepository;
import com.todo_app.CoverageX.service.TaskService;
import com.todo_app.CoverageX.wrappers.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
            return new ResponseEntity<>(new ResponseWrapper<>().responseOk("Created" + savedTask.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseWrapper<>().responseFail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAll(Integer page, Integer size) {

        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<Task> taskPage = taskRepository.findAll(pageable);

            List<TaskDto> dtoList = taskPage.getContent().stream()
                    .map(task -> new TaskDto(task.getTitle(), task.getDescription(), task.isCompleted()))
                    .toList();

            Map<String, Object> response = new HashMap<>();
            response.put("tasks", dtoList);
            response.put("currentPage", taskPage.getNumber());
            response.put("totalItems", taskPage.getTotalElements());
            response.put("totalPages", taskPage.getTotalPages());

            return new ResponseEntity<>(new ResponseWrapper<>().responseOk(response), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseWrapper<>().responseFail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> markTask(Integer id, Integer markId) {

        try {
            Optional<Task> taskOptional = taskRepository.findById(id);
            if (taskOptional.isEmpty()) {
                return new ResponseEntity<>(
                        new ResponseWrapper<>().responseFail("Task not found"),
                        HttpStatus.NOT_FOUND
                );
            }

            Task task = taskOptional.get();

            if (markId == 1) {
                task.setCompleted(true);
            } else {
                task.setCompleted(false);
            }

            taskRepository.save(task);

            return new ResponseEntity<>(
                    new ResponseWrapper<>().responseOk("Success"),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseWrapper<>().responseFail("Error updating task: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
