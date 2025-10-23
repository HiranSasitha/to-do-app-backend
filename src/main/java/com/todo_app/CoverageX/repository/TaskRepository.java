package com.todo_app.CoverageX.repository;

import com.todo_app.CoverageX.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task,Integer> {
}
