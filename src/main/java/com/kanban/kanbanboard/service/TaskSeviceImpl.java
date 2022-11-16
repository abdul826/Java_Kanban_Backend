package com.kanban.kanbanboard.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.kanbanboard.entity.Task;
import com.kanban.kanbanboard.entity.TaskDTO;
import com.kanban.kanbanboard.repository.TaskRepo;

@Service
public class TaskSeviceImpl implements TaskService{

	@Autowired
	private TaskRepo taskRepository;

    @Override
    @Transactional
    public List<Task> getAllTasks() {
        List<Task> tasksList = new ArrayList<>();
        taskRepository.findAll().forEach(tasksList::add);
        return tasksList;
    }

    @Override
    @Transactional
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Task> getTaskByTitle(String title) {
        return taskRepository.findByTitle(title);
    }


    @Override
    @Transactional
    public Task saveNewTask(TaskDTO taskDTO) {
        return taskRepository.save(convertDTOToTask(taskDTO));
    }

    @Override
    @Transactional
    public Task updateTask(Task oldTask, TaskDTO newTaskDTO) {
        return taskRepository.save(updateTaskFromDTO(oldTask, newTaskDTO));
    }

    @Override
    @Transactional
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    private Task convertDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setKanban_Id(taskDTO.getKanban_Id());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        return task;
    }

    private Task updateTaskFromDTO(Task task, TaskDTO taskDTO){
    	if (Optional.ofNullable((taskDTO.getKanban_Id())).isPresent()) {
            task.setKanban_Id(taskDTO.getKanban_Id());
        }
    	
        if(Optional.ofNullable(taskDTO.getTitle()).isPresent()){
            task.setTitle(taskDTO.getTitle());
        }

        if (Optional.ofNullable((taskDTO.getDescription())).isPresent()) {
            task.setDescription(taskDTO.getDescription());
        }

        if (Optional.ofNullable((taskDTO.getStatus())).isPresent()) {
            task.setStatus(taskDTO.getStatus());
        }
        
        
        return task;
    }
    @Override
	public List<Task> getTaskByKanbanId(Long id) {
		// TODO Auto-generated method stub
		List<Task> tasks=taskRepository.findAll();
		List<Task> res=new ArrayList<Task>();
		for(Task task:tasks) {
			if(task.getKanban_Id()==id) {
				res.add(task);
			}
		}
		return res;
	}
}
