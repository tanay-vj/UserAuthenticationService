package org.example.userauthenticationservicemarch2025.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservicemarch2025.models.Role;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String emailId;
    private Role role;

}
