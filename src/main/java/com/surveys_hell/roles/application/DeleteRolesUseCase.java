package com.surveys_hell.roles.application;

import com.surveys_hell.roles.domain.service.RolesService;

public class DeleteRolesUseCase {
    private RolesService rolesService;

    public DeleteRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public void execute(int id) {
        rolesService.deleteRoles(id);
    }

}
