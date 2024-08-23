package com.surveys_hell.response_options.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.response_options.domain.service.ResponseOptionsService;

public class ResponseOptionsRepository implements ResponseOptionsService{
    private Connection connection;

    public ResponseOptionsRepository() {
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
    public void createResponseOptions(ResponseOptions responseOptions) {
        String sql = "INSERT INTO response_options (option_value, category_catalog_id, created_at, parent_response_id, question_id, updated_at, type_component_html, comment_reponse, option_text) VALUES (?, ?, NOW(), ?, ?, NOW(), ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, responseOptions.getOptionValue());
            ps.setInt(2, responseOptions.getCategoryCatalogId());
            ps.setInt(3, responseOptions.getParentResponse());
            ps.setInt(4, responseOptions.getQuestionId());
            ps.setString(5, responseOptions.getTypeComponentHtml());
            ps.setString(6, responseOptions.getOptionText());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteResponseOptions(int id) {
        String sql = "DELETE FROM response_options WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ResponseOptions> findResponseOptions(int id) {
        String sql = "SELECT * FROM response_options WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    ResponseOptions responseOptions = new ResponseOptions(
                        rs.getInt("id"),
                        rs.getInt("option_value"),
                        rs.getInt("category_catalog_id"),
                        rs.getDate("created_at"),
                        rs.getInt("parent_response_id"),
                        rs.getInt("question_id"),
                        rs.getDate("updated_at"),
                        rs.getString("type_component_html"),
                        rs.getString("comment_reponse"),
                        rs.getString("option_text"));
                    return Optional.of(responseOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateResponseOptions(ResponseOptions responseOptions) {
        String sql = "UPDATE response_options SET option_value = ?, category_catalog_id = ?, parent_response_id = ?, question_id = ?, updated_at = NOW(), type_component_html = ?, comment_reponse = ?, option_text = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, responseOptions.getOptionValue());
            ps.setInt(2, responseOptions.getCategoryCatalogId());
            ps.setInt(3, responseOptions.getParentResponse());
            ps.setInt(4, responseOptions.getQuestionId());
            ps.setString(5, responseOptions.getTypeComponentHtml());
            ps.setString(6, responseOptions.getCommentReponse());
            ps.setString(7, responseOptions.getOptionText());
            ps.setInt(8, responseOptions.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ResponseOptions> findResponseByQuestion(int questionId) {
        List<ResponseOptions> responseOptionsList = new ArrayList<>();
        String sql = "SELECT * FROM response_options WHERE question_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ResponseOptions responseOptions = new ResponseOptions(
                            rs.getInt("id"),
                            rs.getInt("option_value"),
                            rs.getInt("category_catalog_id"),
                            rs.getDate("created_at"),
                            rs.getInt("parent_response_id"),
                            rs.getInt("question_id"),
                            rs.getDate("updated_at"),
                            rs.getString("type_component_html"),
                            rs.getString("comment_reponse"),
                            rs.getString("option_text")
                    );
                    responseOptionsList.add(responseOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responseOptionsList;
    }

}
