package com.marat.controlworkbymarat.service;

import com.marat.controlworkbymarat.dto.UserDTO;
import com.marat.controlworkbymarat.entity.User;
import com.marat.controlworkbymarat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PrincipalService principalService;

    @Autowired
    public UserService(UserRepository userRepository, PrincipalService principalService) {
        this.userRepository = userRepository;
        this.principalService = principalService;
    }

    public UserDTO hello(Principal principal){
        User user=principalService.getUserByPrincipal(principal);
        String time="";
        LocalDateTime localDateTime=LocalDateTime.now();
        if(localDateTime.getHour()>=4 && localDateTime.getHour()<12){
            time="Доброе утро "+user.getFio();
            return new UserDTO(time);
        } else if (localDateTime.getHour()>=12 && localDateTime.getHour()<17) {
            time="Добрый день "+user.getFio();
            return new UserDTO(time);
        } else if (localDateTime.getHour()>=17 && localDateTime.getHour()<24) {
            time="Добрый вечер "+user.getFio();
            return new UserDTO(time);
        }
        return new UserDTO(time);
    }
}
