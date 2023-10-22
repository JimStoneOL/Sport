package com.marat.controlworkbymarat.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    private String username;
    private String password;
    public InvalidLoginResponse(){
        this.username="Неправильный email";
        this.password="Неправильный пароль";
    }
}