package com.surveys_pro.login.application;

import java.util.Optional;

import com.surveys_pro.login.domain.entity.LoginUsers;
import com.surveys_pro.login.domain.service.LoginService;

public class LoginAutheticationUseCase {

    private final LoginService loginService;

    public LoginAutheticationUseCase(LoginService loginService) {
        this.loginService = loginService;
    }

    public Optional<LoginUsers> login(String username, String password) {
        return loginService.loginAuthenticateUser(username, password); 
    } 
}
