package org.URLShortner.controllers;

import org.URLShortner.dtos.requests.UserRequest;
import org.URLShortner.dtos.responses.ApiResponse;
import org.URLShortner.dtos.responses.TokenResponse;
import org.URLShortner.dtos.responses.UserResponse;
import org.URLShortner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserRequest userRequest) {
        try {
            UserResponse response = userService.register(userRequest);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserRequest userRequest) {
        try {
            String token = userService.authenticate(userRequest);
            return ResponseEntity.ok(new ApiResponse(new TokenResponse(token), true));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.UNAUTHORIZED);
        }
    }
}
