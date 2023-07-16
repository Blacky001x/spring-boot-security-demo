package com.example.securitydemo.controller;

import com.example.securitydemo.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequest {

    private String name;

    private String email;

    private String password;

    public UserInfo toEntity() {
        final UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setRoles("ROLE_USER");
        return userInfo;

    }
}
