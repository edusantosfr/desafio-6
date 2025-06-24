package com.teach.security.service;

import com.teach.security.dto.req.TaskPatchReqDTO;
import com.teach.security.dto.req.TaskReqDTO;
import com.teach.security.dto.res.TaskResDTO;
import com.teach.security.model.Task;
import com.teach.security.model.User;
import com.teach.security.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResDTO createTask(TaskReqDTO dto) {

        if (dto.titulo() == null)
            throw new RuntimeException("Title cannot be null");

        if (dto.descricao() == null)
            throw new RuntimeException("Description cannot be null");

        if (dto.status() == null)
            throw new RuntimeException("Status cannot be null");


        if (dto.titulo().isEmpty())
            throw new RuntimeException("Title cannot be empty");

        if (dto.descricao().isEmpty())
            throw new RuntimeException("Description cannot be empty");

        if (dto.status().describeConstable().isEmpty())
            throw new RuntimeException("Status cannot be empty");

        Task task = new Task();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(dto.status());
        task.setUsuario(user);

        boolean response = taskRepository.existsByTituloAndUsuario(task.getTitulo(), user);

        if (response) {
            throw new RuntimeException("Task already exists");
        }

        taskRepository.save(task);

        return new TaskResDTO(task.getId(), task.getTitulo(), task.getDescricao(), task.getStatus());
    }

    public List<TaskResDTO> getAllTasks() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Task> tasks = taskRepository.findByUsuario(user);

        return tasks.stream()
                .map(task -> new TaskResDTO(
                        task.getId(),
                        task.getTitulo(),
                        task.getDescricao(),
                        task.getStatus()
                ))
                .toList();
    }

    public TaskResDTO getTaskById(Long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = taskRepository.findByIdAndUsuario(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return new TaskResDTO(task.getId(), task.getTitulo(), task.getDescricao(), task.getStatus());
    }

    public TaskResDTO updateTitleOrStatus(Long id, TaskPatchReqDTO taskPatchReqDTO) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = taskRepository.findByIdAndUsuario(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setDescricao(taskPatchReqDTO.descricao());
        task.setStatus(taskPatchReqDTO.status());

        taskRepository.save(task);

        return new TaskResDTO(task.getId(), task.getTitulo(), task.getDescricao(), task.getStatus());
    }

    public void delete(Long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = taskRepository.findByIdAndUsuario(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }
}
