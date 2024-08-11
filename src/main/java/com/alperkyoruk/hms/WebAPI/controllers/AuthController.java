package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.UserService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.ErrorDataResult;
import com.alperkyoruk.hms.core.result.SuccessDataResult;
import com.alperkyoruk.hms.core.security.JwtService;
import com.alperkyoruk.hms.entities.DTOs.Auth.AuthRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserService userService;

    public AuthController(UserService userService,AuthenticationManager authenticationManager, JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @PostMapping("/generateToken")
    public DataResult<String> generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return new SuccessDataResult<String>(jwtService.generateToken(authRequest.getUsername()), "Token generated successfully");
        }
        return new ErrorDataResult<>("Invalid username or password");
    }
}
