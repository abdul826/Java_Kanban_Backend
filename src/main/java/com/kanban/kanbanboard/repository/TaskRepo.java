package com.kanban.kanbanboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanban.kanbanboard.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	Optional<Task> findByTitle(String title);

}
