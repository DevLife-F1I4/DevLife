package com.example.devlife.security;

import com.example.devlife.dto.UserAccount;
import com.example.devlife.entity.User;
import com.example.devlife.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String providerId) throws UsernameNotFoundException {
        User findUser = userRepository.findByProviderId(providerId);
        if(findUser == null){
            throw new UsernameNotFoundException(providerId);
        }
        return new UserAccount(findUser);
    }
}
