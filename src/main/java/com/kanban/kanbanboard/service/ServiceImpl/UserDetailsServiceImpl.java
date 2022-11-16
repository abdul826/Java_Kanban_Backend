package com.kanban.kanbanboard.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kanban.kanbanboard.entity.User;
import com.kanban.kanbanboard.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         // TODO Auto-generated method stub

         User user = this.userRepository.findByUsername(username); // repo se user ko nikaal rahe hai

         if(user==null){
             throw new UsernameNotFoundException("No User Found");
         }
         return user;
     }
    
}
