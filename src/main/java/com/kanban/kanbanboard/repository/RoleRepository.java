package com.kanban.kanbanboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kanban.kanbanboard.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    
}

