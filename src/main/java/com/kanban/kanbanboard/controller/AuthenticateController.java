package com.kanban.kanbanboard.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kanban.kanbanboard.config.JwtUtils;
import com.kanban.kanbanboard.entity.JwtRequest;
import com.kanban.kanbanboard.entity.JwtResponse;
import com.kanban.kanbanboard.entity.User;
import com.kanban.kanbanboard.service.ServiceImpl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    // generate TOKEN

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("User not found !");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    private void authenticate(String username, String password) throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            // TODO: handle exception
            throw new Exception("User Disable" + e.getMessage());
        } catch(BadCredentialsException e){
            throw new Exception("Invalid Credential" + e.getMessage());
        }
    }

    // Get details of login user

    @GetMapping("/current_user")
    public User getCurrentUser(Principal principal){
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }

}
