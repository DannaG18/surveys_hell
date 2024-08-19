package com.surveys_hell.users.application;

import com.surveys_hell.users.domain.service.UsersService;

public class DeleteUsersUseCase {
    private UsersService usersService;

    public DeleteUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public  void execute(int id) {
        usersService.deleteUsers(id);
    }

}
