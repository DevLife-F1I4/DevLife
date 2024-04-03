package com.example.devlife.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
            super(message);
        }
    public UserNotFoundException() {
            this("존재하지 않는 회원입니다.");
        }
}
