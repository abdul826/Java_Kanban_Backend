package com.kanban.kanbanboard.service;

import java.util.Set;

import com.kanban.kanbanboard.entity.User;
import com.kanban.kanbanboard.entity.UserRole;

public interface UserService {
      // Creating User

      public User createUser(User user, Set<UserRole> userRoles) throws Exception;

      // Get User
  
      public User getUser(String username);
  
      // Delete User
  
      public void deleteUser(Long Id);
}
