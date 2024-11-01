package com.adera.take.home.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.adera.take.home.test.payload.SignupRequest;
import com.adera.take.home.test.entities.User;
import com.adera.take.home.test.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserController {

  @PostMapping("signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return new ResponseEntity<>("User registered Successfully!", HttpStatus.OK);
  }

  @GetMapping("profile")
  public ResponseEntity<User> authenticatedUserProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    User currentUser = (User) authentication.getPrincipal();

    return ResponseEntity.ok(currentUser);
  }

  @GetMapping("profile/update")
  public ResponseEntity<User> authenticatedUserToUpdateProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    User currentUser = (User) authentication.getPrincipal();

    return ResponseEntity.ok(currentUser);
  }

 @GetMapping({"", "status"})
  public String getStatus(){
    return "Application is up and running well";
  }
  
}
