package com.todo_app.CoverageX.controller;

import com.todo_app.CoverageX.dto.TaskDto;
import com.todo_app.CoverageX.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskDto dto){
        return taskService.create(dto);

    }


}
