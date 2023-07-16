package com.example.securitydemo.controller;

import com.example.securitydemo.model.UserInfo;
import com.example.securitydemo.repository.UserInfoRepository;
import com.example.securitydemo.service.JwtService;
import com.example.securitydemo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final JwtService jwtService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public UserController(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        final Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new BadCredentialsException("Not found");
        }
    }

    @PostMapping("/new")
    public String registerUser(@RequestBody UserInfoRequest userInfo) {
        return userService.registerUser(userInfo.toEntity());
    }

}
