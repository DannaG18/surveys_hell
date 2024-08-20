package com.surveys_hell.question.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.question.domain.service.QuestionService;

public class QuestionRepository implements QuestionService{
    private Connection connection;

    public QuestionRepository() {
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
    public void createQuestion(Question question) {
            String sql = "INSERT INTO questions (created_at, updated_at, chapter_id, question_number, response_type, comment_question, question_text) VALUES (NOW(), NOW(), ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, question.getChapterId());
                ps.setString(2, question.getQuestionNumber());
                ps.setString(3, question.getResponseType());
                ps.setString(4, question.getCommentQuestion());
                ps.setString(5, question.getQuestionText());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Question> findQuestion(int id) {
         String sql = "SELECT * FROM questions WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Question question = new Question(rs.getInt("id"), rs.getInt("chapter_id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getString("question_number"), rs.getString("response_type"), rs.getString("comment_question"), rs.getString("question_text"));
                    return Optional.of(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateQuestion(Question question) {
        String sql = "UPDATE questions SET created_at = ?, updated_at = NOW(), chapter_id = ?, question_number = ?, response_type= ?, commment_question = ?, question_text = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, question.getCreatedAt());
            ps.setInt(2, question.getChapterId());
            ps.setString(3, question.getQuestionNumber());
            ps.setString(4, question.getResponseType());
            ps.setString(5, question.getCommentQuestion());
            ps.setString(6, question.getQuestionText());
            ps.setInt(7, question.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
