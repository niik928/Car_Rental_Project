package com.example.security;

import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

       private final UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found"));


            Set<GrantedAuthority> authorities =
                    user.getRole()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toSet());


            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities
            );
        }
    }

