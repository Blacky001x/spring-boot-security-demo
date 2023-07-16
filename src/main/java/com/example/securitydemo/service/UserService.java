package com.example.securitydemo.service;

import com.example.securitydemo.controller.UserInfoRequest;
import com.example.securitydemo.model.UserInfo;
import com.example.securitydemo.repository.UserInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(final UserInfo userInfo) {
        final UserInfo userWithEncodedPassword = encodeUserPassword(userInfo);
        userInfoRepository.save(userWithEncodedPassword);
        return "registered";
    }

    private UserInfo encodeUserPassword(final UserInfo userInfo) {
        final String unencodedPassword = userInfo.getPassword();
        final String encodedPassword = passwordEncoder.encode(unencodedPassword);
        userInfo.setPassword(encodedPassword);
        return userInfo;
    }
}
