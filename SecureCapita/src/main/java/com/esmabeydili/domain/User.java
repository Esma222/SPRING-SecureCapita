package com.esmabeydili.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {


    private Long id;
    @NotEmpty(message ="first name cannot be empty!")
    private String firstName;
    @NotEmpty(message ="last name cannot be empty!")
    private String lastName;
    @NotEmpty(message ="email cannot be empty!")
    @Email(message = "Invalid email. Please enter a valid email address.")
    private String email;
    @NotEmpty(message ="password cannot be empty!")
    private String password;
    private String address;
    private String phone;
    private String title;
    private  String  bio;
    private  String  imageUrl;
    private Boolean enabled;
    private  Boolean isNotLocked;
    private Boolean isUsingMfa;
    private LocalDateTime createdAt;


}
