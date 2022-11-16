package com.kanban.kanbanboard.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanban.kanbanboard.entity.Kanban;

@Repository
public interface KanbanRepo extends JpaRepository<Kanban, Long> {

	Optional<Kanban> findKanbanById(Long id);

	Optional<Kanban> findByTitle(String title);

}
