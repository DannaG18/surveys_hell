package com.surveys_pro.roles.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.surveys_pro.roles.domain.entity.Roles;
import com.surveys_pro.roles.domain.service.RolesService;

public class RolesRepository implements RolesService{
    private Connection connection;

    public RolesRepository() {
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
    public void createRoles(Roles roles) {
            String sql = "INSERT INTO roles (name) VALUES (?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, roles.getName());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Roles> findRoles(int id) {
        String sql = "SELECT id, name FROM roles WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Roles roles = new Roles(rs.getInt("id"), rs.getString("name"));
                    return Optional.of(roles);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateRoles(Roles roles) {
        String sql = "UPDATE roles SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, roles.getName());
            ps.setInt(2, roles.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteRoles(int id) {
        String sql = "DELETE FROM roles WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
