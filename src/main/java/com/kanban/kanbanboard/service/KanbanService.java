package com.kanban.kanbanboard.service;

import java.util.List;
import java.util.Optional;

import com.kanban.kanbanboard.entity.Kanban;
import com.kanban.kanbanboard.entity.KanbanDTO;
import com.kanban.kanbanboard.entity.TaskDTO;

public interface KanbanService {

	List<Kanban> getAllKanbanBoards();

	Optional<Kanban> getKanbanById(Long id);

	Optional<Kanban> getKanbanByTitle(String title);

    Kanban saveNewKanban(KanbanDTO kanbanDto);

    Kanban updateKanban(Kanban oldKanban, KanbanDTO newKanbanDTO);

    void deleteKanban(Kanban kanban);

    Kanban addNewTaskToKanban(Long kanbanId, TaskDTO taskDTO);

}
