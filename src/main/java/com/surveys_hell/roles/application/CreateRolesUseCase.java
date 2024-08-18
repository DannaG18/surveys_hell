package com.surveys_hell.roles.application;

import com.surveys_hell.roles.domain.entity.Roles;
import com.surveys_hell.roles.domain.service.RolesService;

public class CreateRolesUseCase {
    private RolesService rolesService;

    public CreateRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public void execute(Roles roles) {
        rolesService.createRoles(roles);
    }
}