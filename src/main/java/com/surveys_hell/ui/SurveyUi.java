package com.surveys_hell.ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.surveys_hell.survey.domain.service.SurveyService;
import com.surveys_hell.survey.infrastructure.repository.SurveyRepository;
import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.survey.application.FindSurveyByNameUseCase;
import com.surveys_hell.chapter.application.FindChapterBySurveyUseCase;
import com.surveys_hell.response_options.application.FindResponseOptionByQuestionUseCase;
import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.survey.domain.entity.Survey;

public class SurveyUi extends JFrame{
    SurveyService surveyService;
    FindSurveyByNameUseCase findSurveyByNameUseCase;
    FindChapterBySurveyUseCase findChapterBySurveyUseCase;
    FindQuestionByChapterUseCase FindQuestionByChapterUseCase;
    FindResponseOptionByQuestionUseCase findResponseByQuestionUseCase;
    private JPanel contentPanel;
    private JScrollPane scrollPane;

    public SurveyUi(String name) {
        surveyService = new SurveyRepository();
        findSurveyByNameUseCase = new FindSurveyByNameUseCase(surveyService);
        setTitle("Survey Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contentPanel);
        
        
        add(scrollPane, BorderLayout.CENTER);
        findSurveyByNameUseCase.execute(name).ifPresentOrElse(
                survey -> populateSurveyData(survey),
                () -> JOptionPane.showMessageDialog(this, "Survey not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void populateSurveyData(Survey survey) {
        for (Chapter chapter : findChapterBySurveyUseCase.execute(survey.getId())) {
            JPanel chapterPanel = new JPanel();
            chapterPanel.setLayout(new BoxLayout(chapterPanel, BoxLayout.Y_AXIS));
            chapterPanel.setBorder(BorderFactory.createTitledBorder(chapter.getChapterTitle()));

            for (Question question : chapter.getQuestions()) {
                JPanel questionPanel = new JPanel();
                questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
                questionPanel.add(new JLabel(question.getQuestionText()));

                for (ResponseOptions option : findResponseByQuestionUseCase.execute(question.getId())) {
                    questionPanel.add(new JCheckBox(option.getOptionText()));
                }

                chapterPanel.add(questionPanel);
            }

            contentPanel.add(chapterPanel);
        }
    }
}
