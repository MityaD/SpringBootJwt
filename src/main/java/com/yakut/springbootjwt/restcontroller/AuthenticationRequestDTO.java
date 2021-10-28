package com.yakut.springbootjwt.restcontroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String firstName;
    private String password;
}
