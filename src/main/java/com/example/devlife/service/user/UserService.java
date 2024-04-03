package com.example.devlife.service.user;

import com.example.devlife.dto.AuthDto;
import com.example.devlife.dto.UserInfoDto;
import com.example.devlife.entity.Grade;
import com.example.devlife.entity.Role;
import com.example.devlife.entity.User;
import com.example.devlife.exception.DuplicateIdException;
import com.example.devlife.exception.DuplicateNicknameException;
import com.example.devlife.exception.UserNotFoundException;
import com.example.devlife.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * @param signupDto
     */
    @Transactional
    public UserInfoDto.UserResponse signUp(AuthDto.SignUpDto signupDto) {

        // TODO : 아이디 및 닉네임 중복 예외처리 정리하기

        boolean checkId = validateDuplicateId(signupDto.getProviderId());
        boolean checkNickname = validateDuplicateNickname(signupDto.getNickname());

        if(!checkId || !checkNickname) throw new RuntimeException();

        User user = User.builder()
                .providerId(signupDto.getProviderId())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .nickname(signupDto.getNickname())
                .grade(Grade.F1)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return UserInfoDto.UserResponse.from(user);
    }

    /**
     * 유저 정보 조회
     */
    @Transactional(readOnly = true)
    public UserInfoDto.UserResponse getUserInfo(String id) throws UserNotFoundException{
        User user = userRepository.findByProviderId(id);
        if(user==null) throw new UserNotFoundException();
        return UserInfoDto.UserResponse.from(user);
    }
    /**
     * 유저 정보 수정
     */
    @Transactional
    public void updateUserInfo(String id, UserInfoDto.UserRequest requestDto) throws UserNotFoundException{
        User user = userRepository.findByProviderId(id);
        if(user==null) throw new UserNotFoundException();
        validateDuplicateNickname(requestDto.getNickname());
        user.update(requestDto.getNickname());
    }

    public boolean validateDuplicateId(String providerId) {
        if (providerId.equals("")) {
            throw new IllegalArgumentException("ID cannot be blank");
        }
        if (userRepository.existsByProviderId(providerId)) {
            throw new DuplicateIdException();
        }else return true;
    }

    public boolean validateDuplicateNickname(String nickname) {
        if (nickname.equals("")) {
            throw new IllegalArgumentException("nickname cannot be blank");
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }else return true;
    }

    /**
     * 유저 등업
     */
    @Transactional
    public void updateGrade(Long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Grade currentGrade = user.getGrade();
        int newValue = currentGrade.getValue() + 1;
        if(newValue<=2) currentGrade.updateValue(newValue);
        user.updateGrade(currentGrade);
    }



}
