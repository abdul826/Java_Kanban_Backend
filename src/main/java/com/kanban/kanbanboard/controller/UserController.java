package com.kanban.kanbanboard.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanban.kanbanboard.entity.Role;
import com.kanban.kanbanboard.entity.User;
import com.kanban.kanbanboard.entity.UserRole;
import com.kanban.kanbanboard.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    // creating User

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception{

        // Encrypt Password
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("Normal");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        User newUser = this.userService.createUser(user, roles);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    } 

    // get User

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){

        User fetcUser = this.userService.getUser(username);
        return new ResponseEntity<User>(fetcUser, HttpStatus.OK);
    }

    // Delete User

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);

        return new ResponseEntity<User>(HttpStatus.OK);
    }

    
}
