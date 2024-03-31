package com.example.devlife.exception;


public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(String message) {
        super(message);
    }
    public DuplicateIdException() {
        this("이미 존재하는 아이디입니다.");
    }
}
