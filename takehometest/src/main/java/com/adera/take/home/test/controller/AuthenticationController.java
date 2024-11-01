package com.adera.take.home.test.controller;

import com.adera.take.home.test.entities.User;
import com.adera.take.home.test.dtos.RegisterUserDto;
import com.adera.take.home.test.dtos.LoginUserDto;
import com.adera.take.home.test.responses.LoginResponse;
import com.adera.take.home.test.responses.RegistrationResponse;
import com.adera.take.home.test.services.JwtService;
import com.adera.take.home.test.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RequestMapping("/")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    //@RequestMapping(value = "/registration", method = RequestMethod.POST)
    @PostMapping("registration")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        //RegistrationResponse registrasiResponse = new RegistrationResponse().setHttpStatus(HttpStatus.OK.toString());

        return ResponseEntity.ok(registeredUser.printMessage());
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken);

        return ResponseEntity.ok(loginResponse);
    }
}

