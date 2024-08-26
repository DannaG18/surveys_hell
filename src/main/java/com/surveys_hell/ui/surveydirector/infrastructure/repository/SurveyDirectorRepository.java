package com.surveys_hell.ui.surveydirector.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_hell.ui.surveydirector.domain.entity.SurveyDirector;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;

public class SurveyDirectorRepository implements SurveyDirectorService{
    private Connection connection;
    
    public SurveyDirectorRepository() {
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
    public List<Chapter> findChapterBySurvey(int id) {
        List<Chapter> chapters = new ArrayList<>();
        String sql = "select * from chapter where survey_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
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

    @Override
    public List<Question> findQuestionByCategory(int idChapter) {
        List<Question> questions = new ArrayList<>();
        try {
            String query = """
                    SELECT
                        distinct(que.id),
                        que.created_at,
                        que.updated_at,
                        que.question_number,
                        que.response_type,
                        que.comment_question,
                        que.question_text,
                        que.chapter_id,
                        cat.id AS categoria
                    FROM questions que
                    WHERE que.chapter_id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idChapter);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setCreatedAt(rs.getDate("created_at"));
                question.setUpdatedAt(rs.getDate("updated_at"));
                question.setQuestionNumber(rs.getString("question_number"));
                question.setResponseType(rs.getString("response_type"));
                question.setCommentQuestion(rs.getString("comment_question"));
                question.setQuestionText(rs.getString("question_text"));
                question.setChapterId(rs.getInt("chapter_id"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public List<ResponseOptions> findResponseByQuestion(int id) {
        List<ResponseOptions> responses = new ArrayList<>();
        try {
            String query = """
                    SELECT
                        id,
                        created_at,
                        updated_at,
                        option_value,
                        typecomponenthtml,
                        comment_response,
                        option_text,
                        categorycatalog_id,
                        question_id,
                        parentresponse_id
                    FROM response_options
                    WHERE question_id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ResponseOptions response = new ResponseOptions();
                response.setId(rs.getInt("id"));
                response.setOptionText(rs.getString("option_text"));
                responses.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }

    @Override
    public List<SubresponseOptions> findSubresponseByQuestion(int id) {
        List<SubresponseOptions> subresponses = new ArrayList<>();
        try {
            String query = """
                    SELECT
                        id,
                        subresponse_number,
                        created_at,
                        responseoptions_id,
                        updated_at,
                        component_html,
                        subresponse_text
                    FROM subresponse_options
                    WHERE responseoptions_id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubresponseOptions subresponse = new SubresponseOptions();
                subresponse.setId(rs.getInt("id"));
                subresponse.setSubresponseText(rs.getString("subresponse_text"));
                subresponses.add(subresponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return subresponses;
    }

    @Override
    public void createSurveyDirector(SurveyDirector surveyDirector) {
        String query = """
                INSERT INTO response_question (response_id, subresponse_id, responsetext) VALUES (?,?,?)
                """;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, surveyDirector.getResponse_id());

            // Manejar subresponse_id nulo
            if (surveyDirector.getSubresponse_id() == null) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, surveyDirector.getSubresponse_id());
            }

            ps.setString(3, surveyDirector.getResponseText());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
