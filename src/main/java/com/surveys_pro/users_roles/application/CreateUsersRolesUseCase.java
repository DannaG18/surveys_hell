package com.surveys_pro.users_roles.application;

import com.surveys_pro.users_roles.domain.entity.UsersRoles;
import com.surveys_pro.users_roles.domain.service.UsersRolesService;

public class CreateUsersRolesUseCase {
    private UsersRolesService usersRolesService;

    public CreateUsersRolesUseCase(UsersRolesService usersRolesService) {
        this.usersRolesService = usersRolesService;
    }

    public void execute(UsersRoles usersRoles) {
        usersRolesService.createUsersRoles(usersRoles);
    }
}
