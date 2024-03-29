package com.example.devlife.service.user;

import com.example.devlife.dto.AuthDto;
import com.example.devlife.dto.UserDto;
import com.example.devlife.entity.Grade;
import com.example.devlife.entity.Role;
import com.example.devlife.entity.User;
import com.example.devlife.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * @param signupDto
     */
    @Transactional
    public UserDto.UserResponse signUp(AuthDto.SignUpDto signupDto) {

        // TODO : 아이디 및 닉네임 중복 예외처리 정리하기

        if(userRepository.existsByProviderId(signupDto.getProviderId())){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다");
        }

        if (userRepository.existsByNickname(signupDto.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다");
        }

        User user = User.builder()
                .providerId(signupDto.getProviderId())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .nickname(signupDto.getNickname())
                .grade(Grade.F1)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return UserDto.UserResponse.from(user);
    }
}
