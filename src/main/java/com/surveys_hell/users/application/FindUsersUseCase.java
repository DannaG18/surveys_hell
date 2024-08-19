package com.surveys_hell.users.application;

import java.util.Optional;

import com.surveys_hell.users.domain.entity.Users;
import com.surveys_hell.users.domain.service.UsersService;

public class FindUsersUseCase {
    private UsersService usersService;

    public FindUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public Optional<Users> execute(int id) {
        return usersService.findUsers(id);
    }
}
