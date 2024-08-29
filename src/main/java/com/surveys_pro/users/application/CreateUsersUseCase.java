package com.surveys_pro.users.application;

import com.surveys_pro.users.domain.entity.Users;
import com.surveys_pro.users.domain.service.UsersService;

public class CreateUsersUseCase {
    private UsersService usersService;

    public CreateUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public void execute(Users users) {
        usersService.createUsers(users);
    }

}
