package com.surveys_pro.response_question.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.surveys_pro.response_question.domain.entity.ResponseQuestion;
import com.surveys_pro.response_question.domain.service.ResponseQuestionService;

public class ResponseQuestionRepository implements ResponseQuestionService{
    private Connection connection;

    public ResponseQuestionRepository() {
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
    public void createResponseQuestion(ResponseQuestion responseQuestion) {
        String sql = "INSERT INTO response_question (response_id, subresponse_id, response_text) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, responseQuestion.getresponseId());
            ps.setInt(2, responseQuestion.getSubresponseId());
            ps.setString(3, responseQuestion.getresponseText());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteResponseQuestion(int id) {
        String sql = "DELETE FROM response_question WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ResponseQuestion> findResponseQuestion(int id) {
        String sql = "SELECT * FROM response_question WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    ResponseQuestion responseQuestion = new ResponseQuestion (rs.getInt("id"), rs.getInt("response_id"), rs.getInt("subresponse_id"), rs.getString("response_text"));
                    return Optional.of(responseQuestion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateResponseQuestion(ResponseQuestion responseQuestion) {
        String sql = "UPDATE response_question  SET response_id = ?, subresponse_id = ?, response_text = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, responseQuestion.getresponseId());
            ps.setInt(2, responseQuestion.getSubresponseId());
            ps.setString(3, responseQuestion.getresponseText());
            ps.setInt(4, responseQuestion.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
