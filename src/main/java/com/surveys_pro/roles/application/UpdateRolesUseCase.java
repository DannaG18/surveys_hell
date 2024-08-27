package com.surveys_pro.roles.application;

import com.surveys_pro.roles.domain.entity.Roles;
import com.surveys_pro.roles.domain.service.RolesService;

public class UpdateRolesUseCase {
    private RolesService rolesService;

    public UpdateRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public void execute(Roles roles) {
        rolesService.updateRoles(roles);
    }

}
