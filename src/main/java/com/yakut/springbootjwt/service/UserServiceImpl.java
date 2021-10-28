package com.yakut.springbootjwt.service;

import com.yakut.springbootjwt.exception.UserNotSavedToDataBaseException;
import com.yakut.springbootjwt.models.User;
import com.yakut.springbootjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor// todo не та аннотация
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) throws UserNotSavedToDataBaseException {
        try {
            log.info("User saved to DB");
            return userRepository.saveAndFlush(user);//todo ты заебал бездумно копировать баню) там это сделано с определенной целью, и то уже можно выпилить, я сделал другое
        } catch (DataIntegrityViolationException e) {
            throw new UserNotSavedToDataBaseException("Ошибка записи пользователя в базу данных", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        log.info("Getting all Users!");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        log.info("Getting User by id " + id);
        return userRepository.getById(id);//todo лучше использовать findBy
    }

    @Override
    public void deleteAllUsers() {
        log.info("Deleted all Users");
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Delete User by id ");
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(Long id, User user) throws UserNotSavedToDataBaseException {// todo зачем все что там внутри????
        User byDbUser = userRepository.getById(id);
        if (user.getFirstName() != null) byDbUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) byDbUser.setLastName(user.getLastName());
        if (user.getAge() != null) byDbUser.setAge(user.getAge());
        if (user.getPassword() != null) byDbUser.setPassword(user.getPassword());
        if (user.getAddress().getCity() != null) byDbUser.getAddress().setCity(user.getAddress().getCity());
        if (user.getAddress().getHouse() != null) byDbUser.getAddress().setHouse(user.getAddress().getHouse());
        if (user.getAddress().getStreet() != null) byDbUser.getAddress().setStreet(user.getAddress().getStreet());

        log.info("User by id " + byDbUser + " updated and save to database");
        try {
            userRepository.saveAndFlush(byDbUser);// todo сверху уже написал
        } catch (DataIntegrityViolationException e) {
            throw new UserNotSavedToDataBaseException("Ошибка записи пользователя в базу данных при обновлении", e);
        }
    }

}
