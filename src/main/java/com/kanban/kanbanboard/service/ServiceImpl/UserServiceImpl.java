package com.kanban.kanbanboard.service.ServiceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.kanbanboard.entity.User;
import com.kanban.kanbanboard.entity.UserRole;
import com.kanban.kanbanboard.repository.RoleRepository;
import com.kanban.kanbanboard.repository.UserRepository;
import com.kanban.kanbanboard.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        // TODO Auto-generated method stub

       User local = this.userRepository.findByUsername(user.getUsername());

       if(local != null){
        System.out.println("User Already Present");
        throw new Exception("User Already present");
        // throw new UserFoundException();
       }else{

        // create user

        for(UserRole ur:userRoles){     // userRoles se role 'ur' variable me assign kr rahe hai
            roleRepository.save(ur.getRole()); // ur ko save kr rahe hai
        }

        user.getUserRole().addAll(userRoles); // user k andar role set kr rahe hai

        local = this.userRepository.save(user);

       }
        return local;
    }

    // Fetch User

    @Override
    public User getUser(String username) {
        // TODO Auto-generated method stub
        return this.userRepository.findByUsername(username);
    }

    // Delete User

    @Override
    public void deleteUser(Long Id) {
        // TODO Auto-generated method stub

        this.userRepository.deleteById(Id);
        
    }
    
}
