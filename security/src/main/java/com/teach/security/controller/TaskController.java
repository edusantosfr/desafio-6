package com.teach.security.controller;

import com.teach.security.dto.req.TaskPatchReqDTO;
import com.teach.security.dto.req.TaskReqDTO;
import com.teach.security.dto.res.TaskResDTO;
import com.teach.security.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskResDTO createTask(
            @RequestBody TaskReqDTO dto
    ) {
        TaskResDTO response = taskService.createTask(dto);

        return response;
    }

    @GetMapping
    public ResponseEntity<List<TaskResDTO>> getAllTasks() {

        List<TaskResDTO> tasks = taskService.getAllTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResDTO> getTaskById(
            @PathVariable Long id
    ) {
        TaskResDTO task = taskService.getTaskById(id);

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResDTO> updateTitleOrStatus(
            @PathVariable("id") Long id,
            @RequestBody TaskPatchReqDTO taskPatchReqDTO
    ) {
        TaskResDTO task = taskService.updateTitleOrStatus(id, taskPatchReqDTO);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id
            ) {
        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
