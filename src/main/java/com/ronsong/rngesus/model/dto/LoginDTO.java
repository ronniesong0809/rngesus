package com.ronsong.rngesus.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {
    @NotEmpty(message = "username is required")
    @Length(min = 6, max = 20, message = "must be 6 - 20 characters in length")
    private String username;

    @NotEmpty(message = "password is required")
    @Length(min = 6, max = 20, message = "must be 6 - 20 characters in length")
    private String password;

    private Boolean rememberMe;
}