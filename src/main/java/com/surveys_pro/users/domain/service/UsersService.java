package com.surveys_pro.users.domain.service;

import java.util.Optional;

import com.surveys_pro.users.domain.entity.Users;

public interface UsersService {
    void createUsers (Users users);
    Optional<Users> findUsers (int id);
    void updateUsers (Users users);
    void deleteUsers (int id);
}
