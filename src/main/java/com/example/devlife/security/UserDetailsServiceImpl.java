package com.example.devlife.security;

import com.example.devlife.entity.User;
import com.example.devlife.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String providerId) throws UsernameNotFoundException {
        User findUser =  userRepository.findByProviderId(providerId)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with this providerId -> " + providerId));

        if(findUser != null){
            UserDetailsImpl userDetails = new UserDetailsImpl(findUser);
            return  userDetails;
        }

        return null;
    }
}
