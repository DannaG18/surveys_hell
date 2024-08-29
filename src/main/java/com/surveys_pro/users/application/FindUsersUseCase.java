package com.surveys_pro.users.application;

import java.util.Optional;

import com.surveys_pro.users.domain.entity.Users;
import com.surveys_pro.users.domain.service.UsersService;

public class FindUsersUseCase {
    private UsersService usersService;

    public FindUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public Optional<Users> execute(int id) {
        return usersService.findUsers(id);
    }
}
