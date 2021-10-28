package com.yakut.springbootjwt.service;

import com.yakut.springbootjwt.exception.UserNotSavedToDataBaseException;
import com.yakut.springbootjwt.models.User;
import java.util.List;

public interface UserService{

    User saveUser(User user) throws UserNotSavedToDataBaseException;

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteAllUsers();

    void deleteUserById(Long id);

    void updateUser(Long id, User user) throws UserNotSavedToDataBaseException;
}
