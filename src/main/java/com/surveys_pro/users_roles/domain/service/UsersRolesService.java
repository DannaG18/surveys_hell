package com.surveys_pro.users_roles.domain.service;

import java.util.Optional;

import com.surveys_pro.users_roles.domain.entity.UsersRoles;

public interface UsersRolesService {
    void createUsersRoles (UsersRoles usersRoles);
    Optional<UsersRoles> findUsersRoles (int users_id, int roles_id);
    void updateUsersRoles (UsersRoles usersRoles);
    void deleteUsersRoles (int users_id, int roles_id);
}
