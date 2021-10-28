package com.yakut.springbootjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//todo это как-то не похоже на контролллер и наверное не здесь не должно лежать
// todo а data здесь зачем?
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String firstName;
    private String password;
}
