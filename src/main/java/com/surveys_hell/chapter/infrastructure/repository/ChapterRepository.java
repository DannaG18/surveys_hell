package com.surveys_hell.chapter.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.chapter.domain.service.ChapterService;

public class ChapterRepository implements ChapterService{
    private Connection connection;

    public ChapterRepository() {
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
    public void createChapter(Chapter chapter) {
            String sql = "INSERT INTO chapter (created_at, updated_at, survey_id, chapter_number, chapter_title) VALUES (NOW(), NOW(), ?, ?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, chapter.getSurveyId());
                ps.setString(2, chapter.getChapterNumber());
                ps.setString(3, chapter.getChapterTitle());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<Chapter> findChapter(int id) {
         String sql = "SELECT * FROM chapter WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Chapter chapter = new Chapter(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getInt("survey_id"), rs.getString("chapter_number"), rs.getString("chapter_title"));
                    return Optional.of(chapter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateChapter(Chapter chapter) {
        String sql = "UPDATE chapter SET updated_at = NOW(), survey_id = ?, chapter_number = ?, chapter_title = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, chapter.getCreatedAt());
            ps.setInt(2, chapter.getSurveyId());
            ps.setString(3, chapter.getChapterNumber());
            ps.setString(4, chapter.getChapterTitle());
            ps.setInt(5, chapter.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteChapter(int id) {
        String sql = "DELETE FROM chapter WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public List<Chapter> findChapterBySurvey(int surveyId) {
        List<Chapter> chapters = new ArrayList<>();
        String sql = "select * from chapter where survey_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, surveyId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Chapter chapter = new Chapter(rs.getInt("id"), rs.getDate("created_at"), rs.getDate("updated_at"), rs.getInt("survey_id"), rs.getString("chapter_number"), rs.getString("chapter_title"));

                chapters.add(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    
}
