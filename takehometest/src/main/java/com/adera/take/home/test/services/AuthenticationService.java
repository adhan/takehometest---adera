package com.adera.take.home.test.services;

import com.adera.take.home.test.dtos.RegisterUserDto;
import com.adera.take.home.test.dtos.LoginUserDto;
import com.adera.take.home.test.entities.User;
import com.adera.take.home.test.exception.CustomHandledException;
import com.adera.take.home.test.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        var user = new User().setFirstName(input.getFirstName())
                .setEmail(input.getEmail())
                .setLastName(input.getLastName())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        //return userRepository.findByEmail(input.getEmail()).orElseThrow(()-> new CustomHandledException("status" + HttpStatus.BAD_REQUEST, "message : Token tidak tidak valid atau kadaluwarsa"));
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}

