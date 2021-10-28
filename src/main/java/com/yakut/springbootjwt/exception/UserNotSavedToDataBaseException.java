package com.yakut.springbootjwt.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserNotSavedToDataBaseException extends Throwable {
    public UserNotSavedToDataBaseException(String string, DataIntegrityViolationException e) {
    }
}
