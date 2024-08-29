package com.surveys_pro.subresponse_options.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

import com.surveys_pro.dto.SubresponseOptionsDto;
import com.surveys_pro.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_pro.subresponse_options.domain.service.SubresponseOptionsService;

public class SubresponseOptionsRepository implements SubresponseOptionsService{
    private Connection connection;

    public SubresponseOptionsRepository() {
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
    public void createSubresponseOptions(SubresponseOptions subresponseOptions) {
        String sql = "INSERT INTO subresponse_options (subresponse_number, created_at, response_options_id, updated_at, component_html,  subresponse_text) VALUES (?, NOW(), ?, NOW(), 'radio', ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, subresponseOptions.getsubresponseNumber());
            ps.setInt(2, subresponseOptions.getResponseOptionsId());
            ps.setString(3, subresponseOptions.getSubresponseText());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubresponseOptions(int id) {
        String sql = "DELETE FROM subresponse_options WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SubresponseOptions> findSubresponseOptions(int id) {
        String sql = "SELECT * FROM subresponse_options WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    SubresponseOptions subresponseOptions = new SubresponseOptions(rs.getInt("id"), rs.getInt("subresponse_number"), rs.getDate("created_at"), rs.getInt("response_options_id"), rs.getDate("updated_at"), rs.getString("component_html"), rs.getString("subresponse_text"));
                    return Optional.of(subresponseOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateSubresponseOptions(SubresponseOptions subresponseOptions) {
        String sql = "UPDATE subresponse_options SET subresponse_number = ?, response_options_id = ?, updated_at = NOW(), subresponse_text = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, subresponseOptions.getsubresponseNumber());
            ps.setDate(2, subresponseOptions.getCreateAt());
            ps.setInt(3, subresponseOptions.getResponseOptionsId());
            ps.setString(4, subresponseOptions.getSubresponseText());
            ps.setInt(5, subresponseOptions.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<SubresponseOptionsDto> findSubresponseOptionsDto(int id) {
        String sql = """
                    SELECT sub.id, sub.subresponse_text, re.option_text
                    FROM subresponse_options sub
                    JOIN response_options re ON re.id = sub.response_options_id
                    WHERE response_options_id = ? 
                        """;
        List<SubresponseOptionsDto> subresponseOptionsList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    subresponseOptionsList.add(new SubresponseOptionsDto(
                        rs.getInt("id"),
                        rs.getString("subresponse_text"),
                        rs.getString("option_text")
                        ));
                }
                return subresponseOptionsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subresponseOptionsList;
    }
}
