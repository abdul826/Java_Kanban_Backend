package com.kanban.kanbanboard;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kanban.kanbanboard.entity.Role;
import com.kanban.kanbanboard.entity.User;
import com.kanban.kanbanboard.entity.UserRole;
import com.kanban.kanbanboard.service.UserService;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
// @EnableSwagger2
public class KanbanBoardApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(KanbanBoardApplication.class, args);
		System.out.println("Running SpringBoot Aplication");
	}

	// @Bean   
	// public Docket productApi() {    
	// 	return new Docket(DocumentationType.SWAGGER_2)
	// 			.select()
	// 			.apis(RequestHandlerSelectors.any())
	// 			.paths(PathSelectors.any())
	// 			.build();
	// }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting code");

		//User user = new User();

		//user.setFirstname("Abdul");
		//user.setLastname("Rahman");
		//user.setUsername("abdulrahman786");
		//user.setPassword(this.bCryptPasswordEncoder.encode("abdul"));
		//user.setEmail("abdul@gmail.com");
//		user.setProfile("default.png");
//
//		Role role1 = new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("Admin");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		
//		UserRole userRole = new UserRole();
//
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
//		userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
		
	}
}
