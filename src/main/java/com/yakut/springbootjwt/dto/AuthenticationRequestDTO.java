package com.yakut.springbootjwt.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String firstName;
    private String password;
}
