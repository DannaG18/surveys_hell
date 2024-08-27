package com.surveys_pro.login.domain.service;

import java.util.Optional;

import com.surveys_pro.login.domain.entity.LoginUsers;

public interface LoginService {
    public Optional<LoginUsers> loginAuthenticateUser(String username, String password);
    public boolean loginRoleUser(int id);
}
