package com.hotel.fresnel;

import com.hotel.fresnel.exception.RoleAlreadyExistException;
import com.hotel.fresnel.model.Role;
import com.hotel.fresnel.model.User;
import com.hotel.fresnel.repository.RoleRepository;
import com.hotel.fresnel.service.IRoleService;
import com.hotel.fresnel.service.IUserService;
import com.hotel.fresnel.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.Collections;

@SpringBootApplication
public class FresnelApplication {

    public static void main(String[] args) {
        SpringApplication.run(FresnelApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(IRoleService roleService, RoleRepository roleRepository,
                                        IUserService userService) {
        return args -> {
            Role role = new Role();
            role.setId(1L);
            role.setName("user");
            //roleRepository.save(R);
            try {
                roleService.createRole(role);
            }catch (RoleAlreadyExistException ro){
                System.out.println("role already exists"+ ro.getMessage());
            }

            Role role1 = new Role();
            role1.setId(2L);
            role1.setName("Admin");
            try {
                roleService.createRole(role1);
            }catch (RoleAlreadyExistException ro){
                System.out.println("role already exists"+ ro.getMessage());
            }

           User user = new User();
            user.setId(1L);
            user.setFirstName("bill");
            user.setLastName("fres");
            user.setEmail("billy@gmail.com");
            user.setPassword("1234");
            Role userRole = roleRepository.findByName("ROLE_ADMIN").get();
            user.setRoles(Collections.singletonList(userRole));
            userService.saveUser(user);/**/

        };
    }

    @Autowired
    private NotificationService senderService;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {
        senderService.sendSimpleEmail("gofresnel@gmail.com",
                "This is email body",
                "This is email subject");

    }
}
