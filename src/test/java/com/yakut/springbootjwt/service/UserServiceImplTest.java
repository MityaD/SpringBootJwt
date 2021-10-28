package com.yakut.springbootjwt.service;

import com.yakut.springbootjwt.exception.UserNotSavedToDataBaseException;
import com.yakut.springbootjwt.models.User;
import com.yakut.springbootjwt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser() throws UserNotSavedToDataBaseException {
        User user = new User("aaa", "aaaa", 11);
        doReturn(user).when(userRepository).saveAndFlush(user);
        assertEquals(user, userService.saveUser(user));
    }

    @Test
    void getAllUsers() {
        List<User> userList = List.of(
                new User("AAA", "AAAA", 11),
                new User("BBB", "BBBB", 22),
                new User("CCC", "CCCC", 33)
        );
        doReturn(userList).when(userRepository).findAll();
        assertEquals(userList, userRepository.findAll());
    }

    @Test
    void getUserById() {
        List<User> userList = List.of(
                new User(1L,"AAA", "AAAA", 11),
                new User(2L,"BBB", "BBBB", 22)
        );
        when(userRepository.findById(2L)).thenReturn(Optional.of(userList.get(1)));
        assertEquals(userList.get(1), userService.getUserById(2L));
    }

    @Test
    void deleteAllUsers() {
        userService.deleteAllUsers();
        verify(userRepository).deleteAll();
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(1L);
        verify(userRepository).deleteById(1L);
    }
}