package com.kanban.kanbanboard.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TaskDTO {

	@NotBlank(message = "Task title cannot be blank")
	@Size(min=3,message = "Size should not be less than 3")
    private String title;

	@NotBlank(message = "Description cannot be blank")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Long kanban_Id;

	public Long getKanban_Id() {
		return kanban_Id;
	}

	public void setKanban_Id(Long kanban_Id) {
		this.kanban_Id = kanban_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskDTO(String title,String description, TaskStatus status,Long kanban_Id) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.kanban_Id = kanban_Id;
	}

	public TaskDTO() {
		super();
	}

	@Override
	public String toString() {
		return "Task [" +"Title=" + title + ", description=" + description + ", status=" + status + "]";
	}

}
