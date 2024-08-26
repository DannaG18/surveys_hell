package com.surveys_hell.survey_user.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.surveys_hell.survey_user.domain.entity.SurveyUser;
import com.surveys_hell.survey_user.domain.service.SurveyUserService;
import com.surveys_hell.users_roles.domain.entity.UsersRoles;

public class SurveyUserRepository implements SurveyUserService{
    private Connection connection;

    public SurveyUserRepository() {
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
    public void createSurveyUser(SurveyUser surveyUser) {
        String sql = "INSERT INTO survey_user (survey_id, user_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, surveyUser.getSurvey_id());
            ps.setInt(2, surveyUser.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SurveyUser> findSurveyUser(int survey_id, int user_id) {
        String sql = "SELECT survey_id, user_id FROM survey_user WHERE survey_id = ? AND user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, survey_id);
            ps.setInt(2, user_id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    SurveyUser surveyUser = new SurveyUser(rs.getInt("survey_id"), rs.getInt("user_id"));
                    return Optional.of(surveyUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateSurveyUser(SurveyUser surveyUser) {
        String sql = "UPDATE survey_user SET survey_id = ?, user_id = ? WHERE survey_id = ? AND user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, surveyUser.getSurvey_id());
            ps.setInt(2, surveyUser.getUser_id());
            ps.setInt(3, surveyUser.getSurvey_id());
            ps.setInt(4, surveyUser.getUser_id());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteSurveyUser(int survey_id, int user_id) {
        String sql = "DELETE FROM survey_user WHERE survey_id = ? AND user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, survey_id);
            ps.setInt(2, user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
