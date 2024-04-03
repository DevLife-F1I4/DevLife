package com.example.devlife.exception;

public class DuplicateNicknameException extends RuntimeException {
    public DuplicateNicknameException(String message) {
        super(message);
    }
    public DuplicateNicknameException() {
        this("이미 존재하는 닉네임입니다.");
    }
}
