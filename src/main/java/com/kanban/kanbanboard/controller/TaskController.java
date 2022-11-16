package com.kanban.kanbanboard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanban.kanbanboard.entity.Task;
import com.kanban.kanbanboard.entity.TaskDTO;
import com.kanban.kanbanboard.service.TaskService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	
	@GetMapping("/getAll")
    public ResponseEntity<?> getAllTasks(){
        try {
            return new ResponseEntity<>(
                    taskService.getAllTasks(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id){
        try {
            Optional<Task> optTask = taskService.getTaskById(id);
            if (optTask.isPresent()) {
                return new ResponseEntity<>(
                        optTask.get(),
                        HttpStatus.OK);
            } else {
                return noTaskFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/getbytitle/{title}")
    public ResponseEntity<?> getTaskByTitle(@PathVariable String title){
        try {
            Optional<Task> optTask = taskService.getTaskByTitle(title);
            if (optTask.isPresent()) {
                return new ResponseEntity<>(
                        optTask.get(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No task found with a title: " + title, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }
    
    @GetMapping("/getByKanbanId/{id}")
    public List<Task> getTaskByKanbanId(@PathVariable Long id){
    	return taskService.getTaskByKanbanId(id);
    }
    
    @PostMapping("/createtask")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO){
        try {
            return new ResponseEntity<>(
                    taskService.saveNewTask(taskDTO),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        try {
            Optional<Task> optTask = taskService.getTaskById(id);
            if (optTask.isPresent()) {
                return new ResponseEntity<>(
                        taskService.updateTask(optTask.get(), taskDTO),
                        HttpStatus.OK);
            } else {
                return noTaskFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        try {
            Optional<Task> optTask = taskService.getTaskById(id);
            if (optTask.isPresent()) {
                taskService.deleteTask(optTask.get());
                return new ResponseEntity<>(String.format("Task with id: %d was deleted", id), HttpStatus.OK);
            } else {
                return noTaskFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noTaskFoundResponse(Long id){
        return new ResponseEntity<>("No task found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
