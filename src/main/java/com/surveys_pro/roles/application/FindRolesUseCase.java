package com.surveys_pro.roles.application;

import java.util.Optional;

import com.surveys_pro.roles.domain.entity.Roles;
import com.surveys_pro.roles.domain.service.RolesService;

public class FindRolesUseCase {
    private RolesService rolesService;

    public FindRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public Optional<Roles> execute(int id) {
        return rolesService.findRoles(id);
    }
}
