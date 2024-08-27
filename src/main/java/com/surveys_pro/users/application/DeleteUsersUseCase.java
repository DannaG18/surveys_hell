package com.surveys_pro.users.application;

import com.surveys_pro.users.domain.service.UsersService;

public class DeleteUsersUseCase {
    private UsersService usersService;

    public DeleteUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public  void execute(int id) {
        usersService.deleteUsers(id);
    }

}
