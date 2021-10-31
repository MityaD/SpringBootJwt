package com.yakut.springbootjwt.service;

import com.yakut.springbootjwt.exception.NoUserUnderThisId;
import com.yakut.springbootjwt.exception.UserNotSavedToDataBaseException;
import com.yakut.springbootjwt.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    User saveUser(User user) throws UserNotSavedToDataBaseException;

    List<User> getAllUsers();

    User findUserById(Long id) throws NoUserUnderThisId;

    void deleteAllUsers();

    void deleteUserById(Long id);

    @Override
    User loadUserByUsername(String firstName) throws UsernameNotFoundException;
}
