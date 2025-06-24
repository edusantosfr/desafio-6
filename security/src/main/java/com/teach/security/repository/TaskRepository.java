package com.teach.security.repository;

import com.teach.security.model.Task;
import com.teach.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTituloAndUsuario(String titulo, User usuario);

    List<Task> findByUsuario(User usuario);

    Optional<Task> findByIdAndUsuario(Long id, User usuario);
}
