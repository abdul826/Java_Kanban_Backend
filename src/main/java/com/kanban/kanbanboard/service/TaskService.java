package com.kanban.kanbanboard.service;

import java.util.List;
import java.util.Optional;

import com.kanban.kanbanboard.entity.Task;
import com.kanban.kanbanboard.entity.TaskDTO;

public interface TaskService {

	List<Task> getAllTasks();

    Optional<Task> getTaskById(Long id);

    Optional<Task> getTaskByTitle(String title);

    Task saveNewTask(TaskDTO taskDTO);

    Task updateTask(Task oldTask, TaskDTO newTaskDTO);

    void deleteTask(Task task);
    List<Task> getTaskByKanbanId(Long id);
}
