package com.surveys_hell.roles.application;

import com.surveys_hell.roles.domain.entity.Roles;
import com.surveys_hell.roles.domain.service.RolesService;

public class UpdateRolesUseCase {
    private RolesService rolesService;

    public UpdateRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public void execute(Roles roles) {
        rolesService.updateRoles(roles);
    }

}
