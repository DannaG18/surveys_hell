package com.surveys_pro.roles.domain.service;

import java.util.Optional;

import com.surveys_pro.roles.domain.entity.Roles;

public interface RolesService {
    void createRoles (Roles roles);
    Optional<Roles> findRoles (int id);
    void updateRoles (Roles roles);
    void deleteRoles (int id);
}
