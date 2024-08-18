package com.surveys_hell.roles.domain.service;

import java.util.Optional;

import com.surveys_hell.roles.domain.entity.Roles;

public interface RolesService {
    void createRoles (Roles roles);
    Optional<Roles> findRoles (int id);
    void updateRoles (Roles roles);
    void deleteRoles (int id);
}
