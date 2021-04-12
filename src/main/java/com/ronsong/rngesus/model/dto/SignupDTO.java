package com.ronsong.rngesus.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class SignupDTO {
    @NotEmpty(message = "Please enter a username")
    @Length(min = 2, max = 10, message = "Must be 2 - 10 characters in length")
    private String username;

    @NotEmpty(message = "Please enter the password")
    @Length(min = 6, max = 20, message = "Must be 6 - 20 characters in length")
    private String password;

    @NotEmpty(message = "Please enter the password again")
    @Length(min = 6, max = 20, message = "Must be 6 - 20 characters in length")
    private String confirm;

    @NotEmpty(message = "Please enter an email address")
    @Email(message = "Please enter a valid email address")
    private String email;
}
