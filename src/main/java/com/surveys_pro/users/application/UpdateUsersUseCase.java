package com.surveys_pro.users.application;

import com.surveys_pro.users.domain.entity.Users;
import com.surveys_pro.users.domain.service.UsersService;

public class UpdateUsersUseCase {
    private UsersService usersService;

    public UpdateUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public void execute(Users users) {
        usersService.updateUsers(users);
    }
}
