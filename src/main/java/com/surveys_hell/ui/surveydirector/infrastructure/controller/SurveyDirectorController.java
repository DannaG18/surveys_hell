package com.surveys_hell.ui.surveydirector.infrastructure.controller;

import javax.swing.*;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.survey.domain.entity.Survey;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;
import com.surveys_hell.ui.surveydirector.infrastructure.repository.SurveyDirectorRepository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class SurveyDirectorController extends JFrame {
    
    private final SurveyDirectorService surveyDirectorService; // Servicio para manejar los casos de uso

    public SurveyDirectorController(String name) {
        // Inicializar servicio (inyectar o crear dependiendo de tu arquitectura)
        this.surveyDirectorService = new SurveyDirectorRepository();
    
        setTitle("Survey");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Panel principal con scroll
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
        // Scroll pane que contiene el panel principal
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    
        // Cargar y mostrar la encuesta
        loadSurveyData(mainPanel, name);
    }
    
    private void loadSurveyData(JPanel mainPanel, String surveyName) {
        // Usar el caso de uso para obtener la encuesta por nombre
        Optional<Survey> survey = surveyDirectorService.findSurvey(surveyName);
        if (survey.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Survey not found");
            return;
        }
        
        // Título de la encuesta
        JLabel surveyTitle = new JLabel(survey.getName());
        surveyTitle.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(surveyTitle);
    
        // Obtener capítulos asociados a la encuesta
        List<Chapter> chapters = surveyDirectorService.findChapterBySurvey(survey.getId());
    
        for (Chapter chapter : chapters) {
            // Título del capítulo
            JLabel chapterTitle = new JLabel(chapter.getChapterTitle());
            chapterTitle.setFont(new Font("Arial", Font.BOLD, 18));
            mainPanel.add(chapterTitle);
    
            // Obtener preguntas asociadas al capítulo
            List<Question> questions = surveyDirectorService.findQuestionByCategory(chapter.getId());
    
            for (Question question : questions) {
                // Texto de la pregunta
                JLabel questionLabel = new JLabel(question.getQuestionText());
                questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                mainPanel.add(questionLabel);
    
                // Obtener opciones de respuesta asociadas a la pregunta
                List<ResponseOptions> responses = surveyDirectorService.findResponseByQuestion(question.getId());
    
                for (ResponseOptions response : responses) {
                    JCheckBox responseCheckBox = new JCheckBox(response.getOptionText());
                    mainPanel.add(responseCheckBox);
                }
            }
        }
    }
}