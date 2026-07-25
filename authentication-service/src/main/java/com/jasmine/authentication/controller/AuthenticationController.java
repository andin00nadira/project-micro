package com.jasmine.authentication.controller;

import com.jasmine.authentication.login.LoginRequest;
import com.jasmine.authentication.login.LoginResponse;
import com.jasmine.authentication.register.RegisterRequest;
import com.jasmine.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authenticationService.login(
                request.getUsername(),
                request.getPassword());
    }
}
