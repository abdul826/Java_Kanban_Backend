package com.kanban.kanbanboard.entity;

import javax.persistence.Entity;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "kanban")
public class Kanban {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@NotBlank(message = "Title cannot be blank")
    @Size(min=3,message= "Title should have atleast 3 characters")
    private String title;

    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "kanban_Id")
    private List<Task> tasks;

    public void addTask(Task task) {

        if (Objects.isNull(tasks)) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Kanban(Long id, String title, List<Task> tasks) {
		super();
		this.id = id;
		this.title = title;
		this.tasks = tasks;
	}

	public Kanban() {
		super();
	}
    
    
}
