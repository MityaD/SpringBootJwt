package com.yakut.springbootjwt.service;

import com.yakut.springbootjwt.exception.NoUserUnderThisId;
import com.yakut.springbootjwt.exception.UserNotSavedToDataBaseException;
import com.yakut.springbootjwt.models.User;
import com.yakut.springbootjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
            log.info("Запись пользователя в базу данных");
            return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        log.info("Получение всех пользователей!");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Long id) throws NoUserUnderThisId {
        log.info("Получение пользователя по id: " + id);
        return userRepository.findById(id).orElseThrow(() ->
                new NoUserUnderThisId("Нет пользователя под таким id: " + id));
    }

    @Override
    public void deleteAllUsers() {
        log.info("Удаление всех пользователей из базы данных");
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Удаление пользователя по id: " + id);
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String firstName) throws UsernameNotFoundException {
        return userRepository.findByFirstName(firstName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }
}
