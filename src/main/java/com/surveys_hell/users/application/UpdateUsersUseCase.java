package com.surveys_hell.users.application;

import com.surveys_hell.users.domain.entity.Users;
import com.surveys_hell.users.domain.service.UsersService;

public class UpdateUsersUseCase {
    private UsersService usersService;

    public UpdateUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public void execute(Users users) {
        usersService.updateUsers(users);
    }
}
