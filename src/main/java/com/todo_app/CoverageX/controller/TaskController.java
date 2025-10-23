package com.todo_app.CoverageX.controller;

import com.todo_app.CoverageX.dto.TaskDto;
import com.todo_app.CoverageX.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TaskDto dto){
        return taskService.create(dto);

    }


    @GetMapping("/get-task/{page}/{size}")
    public ResponseEntity<?>getTask(@PathVariable Integer page,@PathVariable Integer size){
        return taskService.getAll(page,size);
    }


    @PutMapping("update/{id}/{markId}") // (if 1 mark as completed , if 0 mark as not completed )
    public ResponseEntity<?> markTask(@PathVariable Integer id,@PathVariable Integer markId){
        return taskService.markTask(id,markId);
    }

    @GetMapping("/count")
    public ResponseEntity<?>getCount(){
        return taskService.getCount();
    }


}
