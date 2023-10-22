package com.marat.controlworkbymarat.payload.response;


import com.marat.controlworkbymarat.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserRoleResponse {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private Set<Role> role;
}
