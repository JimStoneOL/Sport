package com.marat.controlworkbymarat.web;

import com.marat.controlworkbymarat.dto.UserDTO;
import com.marat.controlworkbymarat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public UserDTO hello(Principal principal){
        return userService.hello(principal);
    }
}
