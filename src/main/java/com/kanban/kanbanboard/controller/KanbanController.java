package com.kanban.kanbanboard.controller;


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

import com.kanban.kanbanboard.entity.Kanban;
import com.kanban.kanbanboard.entity.KanbanDTO;
import com.kanban.kanbanboard.service.KanbanService;

@RestController
@RequestMapping("/kanban")
@CrossOrigin(origins = "*")
public class KanbanController {

	@Autowired
	private KanbanService kanbanService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllKanbans(){
        try {
            return new ResponseEntity<>(
                    kanbanService.getAllKanbanBoards(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getKanban(@PathVariable Long id){
        try {
            Optional<Kanban> optKanban = kanbanService.getKanbanById(id);
            if (optKanban.isPresent()) {
                return new ResponseEntity<>(
                        optKanban,
                        HttpStatus.OK);
            } else {
                return noKanbanFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/getbytitle/{title}")
    public ResponseEntity<?> getKanbanByTitle(@PathVariable String title){
        try {
            Optional<Kanban> optKanban = kanbanService.getKanbanByTitle(title);
            if (optKanban.isPresent()) {
                return new ResponseEntity<>(
                        optKanban,
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No kanban found with a title: " + title, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/createkanban")
    public ResponseEntity<?> createKanban(@RequestBody KanbanDTO kanbanDTO){
        try {
            return new ResponseEntity<>(
                    kanbanService.saveNewKanban(kanbanDTO),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<?> updateKanban(@PathVariable Long id, @RequestBody KanbanDTO kanbanDTO){
        //try {
        	Optional<Kanban> lstKanban = kanbanService.getKanbanById(id);
            Kanban optKanban = kanbanService.getKanbanById(id).get();
            if (lstKanban.isPresent()) {
            	System.out.println("Aaya"); //working
            	
               ResponseEntity<?> re =  new ResponseEntity<>(
                        kanbanService.updateKanban(optKanban, kanbanDTO),
                        HttpStatus.OK);
               System.out.println("hello");
               return re;
            } else {
                return noKanbanFoundResponse(id);
            }
        //} 
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> deleteKanban(@PathVariable Long id){
        try {
            Optional<Kanban> optKanban = kanbanService.getKanbanById(id);
            Kanban object = kanbanService.getKanbanById(id).get();
            if (optKanban.isPresent()) {
                kanbanService.deleteKanban(object);
                return new ResponseEntity<>(
                        String.format("Kanban with id: %d was deleted", id),
                        HttpStatus.OK);
            } else {
                return noKanbanFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
       
    }
    
    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noKanbanFoundResponse(Long id){
        return new ResponseEntity<>("No kanban found with id: " + id, HttpStatus.NOT_FOUND);
    }
}