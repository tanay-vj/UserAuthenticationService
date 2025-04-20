package org.example.userauthenticationservicemarch2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDto {
    String token;
    Long userId;
}
