package com.marat.controlworkbymarat;

import com.marat.controlworkbymarat.entity.Role;
import com.marat.controlworkbymarat.entity.enums.ERole;
import com.marat.controlworkbymarat.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ControlWorkByMaratApplication {

    @Autowired
    private RoleRepository roleRepository;


    public static void main(String[] args) {
        SpringApplication.run(ControlWorkByMaratApplication.class, args);
    }
    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            if (
                    roleRepository.findByName(ERole.ROLE_ADMIN).isPresent() &&
                            roleRepository.findByName(ERole.ROLE_USER).isPresent()
            ) {

            } else {
                roleRepository.deleteAll();
                Role user = new Role();
                user.setName(ERole.ROLE_USER);
                roleRepository.save(user);
                Role admin = new Role();
                admin.setName(ERole.ROLE_ADMIN);
                roleRepository.save(admin);
            }
        };
    }
}
