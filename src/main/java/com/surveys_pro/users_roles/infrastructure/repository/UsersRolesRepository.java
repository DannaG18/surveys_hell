package com.surveys_pro.users_roles.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.surveys_pro.users_roles.domain.entity.UsersRoles;
import com.surveys_pro.users_roles.domain.service.UsersRolesService;

public class UsersRolesRepository implements UsersRolesService{
    private Connection connection;

    public UsersRolesRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUsersRoles(UsersRoles usersRoles) {
        String sql = "INSERT INTO user_roles (role_id, user_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, usersRoles.getRole_id());
            ps.setInt(2, usersRoles.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<UsersRoles> findUsersRoles(int users_id, int roles_id) {
        String sql = "SELECT role_id, user_id FROM user_roles WHERE user_id = ? AND role_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, users_id);
            ps.setInt(2, roles_id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    UsersRoles usersRoles = new UsersRoles(rs.getInt("role_id"), rs.getInt("user_id"));
                    return Optional.of(usersRoles);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateUsersRoles(UsersRoles usersRoles) {
        String sql = "UPDATE user_roles SET role_id = ?, user_id = ? WHERE role_id = ? AND user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, usersRoles.getRole_id());
            ps.setInt(2, usersRoles.getUser_id());
            ps.setInt(3, usersRoles.getRole_id());
            ps.setInt(4, usersRoles.getUser_id());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteUsersRoles(int users_id, int roles_id) {
        String sql = "DELETE FROM user_roles WHERE user_id = ? AND role_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, users_id);
            ps.setInt(2, roles_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
