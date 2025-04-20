package org.example.userauthenticationservicemarch2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    String emailId;
    String password;
}
