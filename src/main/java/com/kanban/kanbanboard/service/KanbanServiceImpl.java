package com.kanban.kanbanboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.kanbanboard.entity.Kanban;
import com.kanban.kanbanboard.entity.KanbanDTO;
import com.kanban.kanbanboard.entity.Task;
import com.kanban.kanbanboard.entity.TaskDTO;
import com.kanban.kanbanboard.repository.KanbanRepo;

@Service
public class KanbanServiceImpl implements KanbanService {

	@Autowired
	private KanbanRepo kanbanRepo;
	@Override
	public List<Kanban> getAllKanbanBoards() {
		// TODO Auto-generated method stub
		return kanbanRepo.findAll();
	}

	@Override
	public Optional<Kanban> getKanbanById(Long id) {
		// TODO Auto-generated method stub
		return kanbanRepo.findKanbanById(id);
	}

	@Override
	public Optional<Kanban> getKanbanByTitle(String title) {
		// TODO Auto-generated method stub
		return kanbanRepo.findByTitle(title);
	}

	@Override
	public Kanban saveNewKanban(KanbanDTO kanbanDto) {
		// TODO Auto-generated method stub
		return kanbanRepo.save(convertDTOToKanban(kanbanDto));
	}

	@Override
	public Kanban updateKanban(Kanban oldKanban, KanbanDTO newKanbanDTO) {
		// TODO Auto-generated method stub
		System.out.println("Hyy service"); //not working
		oldKanban.setTitle(newKanbanDTO.getTitle());
		return kanbanRepo.save(oldKanban);
	}

	@Override
	public void deleteKanban(Kanban kanban) {
		// TODO Auto-generated method stub
		kanbanRepo.delete(kanban);
	}

	@Override
	public Kanban addNewTaskToKanban(Long kanbanId, TaskDTO taskDTO) {
		// TODO Auto-generated method stub
		Kanban kanban = kanbanRepo.findById(kanbanId).get();
        kanban.addTask(convertDTOToTask(taskDTO));
        return kanbanRepo.save(kanban);
	}

	private Kanban convertDTOToKanban(KanbanDTO kanbanDTO){
        Kanban kanban = new Kanban();
        kanban.setTitle(kanbanDTO.getTitle());
        return kanban;
    }

    private Task convertDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        return task;
    }
	
}
