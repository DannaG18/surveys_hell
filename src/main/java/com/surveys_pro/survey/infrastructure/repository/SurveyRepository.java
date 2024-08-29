package com.surveys_pro.survey.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveys_pro.chapter.infrastructure.repository.ChapterRepository;
import com.surveys_pro.dto.SurveyDto;
import com.surveys_pro.survey.domain.entity.Survey;
import com.surveys_pro.survey.domain.service.SurveyService;

public class SurveyRepository implements SurveyService{
    private Connection connection;
     private ChapterRepository chapterRepository;

    public SurveyRepository() {
        this.chapterRepository = new ChapterRepository();
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
    public void createSurvey(Survey survey) {
            String sql = "INSERT INTO surveys (created_at, updated_at, description, name) VALUES (NOW(), NOW(), ?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, survey.getDescription());
                ps.setString(2, survey.getName());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Survey> findSurvey(int id) {
        String sql = "SELECT * FROM surveys WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Survey survey = new Survey(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("description"), rs.getString("name"));
                    return Optional.of(survey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateSurvey(Survey survey) {
        String sql = "UPDATE surveys SET created_at = ?, updated_at = NOW(), description = ?, name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, survey.getCreatedAt());
            ps.setString(2, survey.getDescription());
            ps.setString(3, survey.getName());
            ps.setInt(4, survey.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteSurvey(int id) {
        String sql = "DELETE FROM surveys WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void getAllSurvey(List<Survey> surveys) {
        String sql = "SELECT * FROM surveys";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Survey survey = new Survey(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("description"), rs.getString("name"));
                    surveys.add(survey);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Survey> findSurveyByName(String name) {
        String sql = "SELECT * FROM surveys WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Survey survey = new Survey(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("description"), rs.getString("name"));
                    return Optional.of(survey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SurveyDto> findSurveyDto(int id) {
        String sql = "SELECT * FROM surveys WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) { 
                    SurveyDto surveyDto = new SurveyDto(rs.getInt("id"), rs.getString("name"), chapterRepository.findChapterDto(rs.getInt("id")));
                    return Optional.of(surveyDto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
