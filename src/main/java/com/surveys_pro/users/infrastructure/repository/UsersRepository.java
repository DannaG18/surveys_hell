package com.surveys_pro.users.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.surveys_pro.users.domain.entity.Users;
import com.surveys_pro.users.domain.service.UsersService;

public class UsersRepository implements UsersService{
    private Connection connection;

    public UsersRepository() {
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
    public void createUsers(Users users) {
        String sql = "INSERT INTO users (enabled, username, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, users.isEnabled());
            ps.setString(2, users.getUsername());
            ps.setString(3, users.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Users> findUsers(int id) {
        String sql = "SELECT id, enabled, username, password FROM users WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Users users = new Users(rs.getInt("id"), rs.getBoolean("enabled"), rs.getString("username"), rs.getString("password"));
                    return Optional.of(users);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateUsers(Users users) {
        String sql = "UPDATE users SET enabled = ?, username = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, users.isEnabled());
            ps.setString(2, users.getUsername());
            ps.setString(3, users.getPassword());
            ps.setInt(4, users.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteUsers(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
}
