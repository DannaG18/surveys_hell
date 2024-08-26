package com.surveys_hell.ui.surveydirector.infrastructure.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.surveys_hell.categories_catalog.application.GetAllCategoriesUseCase;
import com.surveys_hell.categories_catalog.domain.entity.Category;
import com.surveys_hell.categories_catalog.domain.service.CategoryService;
import com.surveys_hell.categories_catalog.infrastructure.repository.CategoryRepository;
import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.response_options.application.FindResponseOptionsUseCase;
import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.response_options.domain.service.ResponseOptionsService;
import com.surveys_hell.response_options.infrastructure.repository.ResponseOptionsRepository;
import com.surveys_hell.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_hell.survey.application.GetAllSurveyUseCase;
import com.surveys_hell.survey.domain.entity.Survey;
import com.surveys_hell.survey.domain.service.SurveyService;
import com.surveys_hell.survey.infrastructure.repository.SurveyRepository;
import com.surveys_hell.survey_user.application.CreateSurveyUserUseCase;
import com.surveys_hell.survey_user.domain.service.SurveyUserService;
import com.surveys_hell.survey_user.infrastructure.repository.SurveyUserRepository;
import com.surveys_hell.ui.surveydirector.application.CreateSurveyDIrectorUseCase;
import com.surveys_hell.ui.surveydirector.application.FindAllSubresponseUseCase;
import com.surveys_hell.ui.surveydirector.application.FindChapterUseCase;
import com.surveys_hell.ui.surveydirector.application.FindQuestionUseCase;
import com.surveys_hell.ui.surveydirector.application.FindResponseUseCase;
import com.surveys_hell.ui.surveydirector.domain.entity.SurveyDirector;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;
import com.surveys_hell.ui.surveydirector.infrastructure.repository.SurveyDirectorRepository;

public class SurveyDirectorController extends JFrame{
    SurveyDirectorService surveyDirectorService;

    SurveyService surveyService;
    GetAllSurveyUseCase getAllSurveyUseCase;

    CategoryService categoryService;
    GetAllCategoriesUseCase getAllCategoriesUseCase;

    ResponseOptionsService responseOptionsService;
    FindResponseOptionsUseCase findResponseOptionsUseCase;

    SurveyUserService surveyUserService;
    CreateSurveyUserUseCase createSurveyUserUseCase;

    FindChapterUseCase findChapterUseCase;
    FindQuestionUseCase findQuestionUseCase;
    FindResponseUseCase findResponseUseCase;
    FindAllSubresponseUseCase findAllSubresponseUseCase;
    CreateSurveyDIrectorUseCase createSurveyDIrectorUseCase;

    JComboBox<String> nameComboBox;
    DefaultComboBoxModel<String> comboBoxModel;
    JPanel contentPanel;
    JScrollPane scrollPane;

    public SurveyDirectorController() {
        surveyDirectorService = new SurveyDirectorRepository();
        responseOptionsService = new ResponseOptionsRepository();
        surveyService = new SurveyRepository();
        categoryService = new CategoryRepository();
        surveyUserService = new SurveyUserRepository();

        getAllSurveyUseCase = new GetAllSurveyUseCase(surveyService);
        getAllCategoriesUseCase = new GetAllCategoriesUseCase(categoryService);
        findResponseOptionsUseCase = new FindResponseOptionsUseCase(responseOptionsService);
        createSurveyUserUseCase = new CreateSurveyUserUseCase(surveyUserService);
        findChapterUseCase = new FindChapterUseCase(surveyDirectorService);
        findQuestionUseCase = new FindQuestionUseCase(surveyDirectorService);
        findResponseUseCase = new FindResponseUseCase(surveyDirectorService);
        findAllSubresponseUseCase = new FindAllSubresponseUseCase(surveyDirectorService);
        createSurveyDIrectorUseCase = new CreateSurveyDIrectorUseCase(surveyDirectorService);

        setTitle("Survey Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contentPanel);
        
        
        add(scrollPane, BorderLayout.CENTER);
    }

    public void start(int user_id) {
        int opt = 0;
        String opts = "1. Contestar Encuesta\n2. Volver";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    SurveyDirector surveyDirector = new SurveyDirector();
                    int idSurvey = showSurvey();
                    int idChapter = showChapters(findChapterUseCase.execute(idSurvey));
                    int idcat = showCategories(getAllCategoriesUseCase.execute());
                    int idQue = showQuestions(findQuestionUseCase.execute(idChapter));
                    ResponseOptions response = showResponseOpt(findResponseUseCase.execute(idQue));
                    int idSub = showSubResponseOpt(findAllSubresponseUseCase.execute(response.getId()));

                    surveyDirector.setResponse_id(response.getId());

                    // Convertir 0 a null antes de asignar
                    Integer subresponseId = (idSub == 0) ? null : idSub;
                    surveyDirector.setSubresponse_id(subresponseId);

                    surveyDirector.setResponseText(response.getOptionText());
                    createSurveyDIrectorUseCase.execute(surveyDirector);

                    break;
                case 2:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada");
                    break;
            }
        } while (opt != 2);
    }

    public int showSurvey() {
        Map<String, Integer> map = new HashMap<>();
        comboBoxModel = new DefaultComboBoxModel<>();
        nameComboBox = new JComboBox<>(comboBoxModel);
        nameComboBox.setPreferredSize(new Dimension(100, 30));
        comboBoxModel.removeAllElements(); 
        comboBoxModel.addElement("Select an option");

        List<Survey> surveys = new ArrayList<>(); 
        getAllSurveyUseCase.execute(surveys);
        for (Survey survey : surveys) {
            String row = survey.getId() + " - " + survey.getName();
            comboBoxModel.addElement(row);
            map.put(row, survey.getId());
        }
        int result = JOptionPane.showConfirmDialog(null, nameComboBox, "Seleccione la encuesta",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) comboBoxModel.getSelectedItem();
            int id = map.get(text);
            return id;
        }
        return 0;
    }

    public void showEncuesta(int survey_id) {
        JPanel surveyPanel = new JPanel();
        surveyPanel.setLayout(new BoxLayout(surveyPanel, BoxLayout.Y_AXIS));
        JButton button = new JButton("Finish");
        surveyPanel.add(button, BorderLayout.SOUTH);
        contentPanel.add(surveyPanel, BorderLayout.CENTER);
        for (Chapter chapter : findChapterUseCase.execute(survey_id)) {
            JPanel chapterPanel = new JPanel();
            chapterPanel.setLayout(new BoxLayout(chapterPanel, BoxLayout.Y_AXIS));
            chapterPanel.setBorder(BorderFactory.createTitledBorder(chapter.getChapterNumber() + " - " + chapter.getChapterTitle()));

            for (Question question : findQuestionUseCase.execute(chapter.getId())) {
                JPanel questionPanel = new JPanel();
                questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
                questionPanel.add(new JLabel(question.getQuestionNumber() + " - " + question.getQuestionText()));

                for (ResponseOptions option : findResponseUseCase.execute(question.getId())) {
                    questionPanel.add(new JCheckBox(option.getOptionText()));

                    for (SubresponseOptions subOption : findAllSubresponseUseCase.execute(option.getId())) {
                        if (subOption != null) {
                            questionPanel.add(new JCheckBox(subOption.getSubresponseText()));
                        }
                    }
                }
                chapterPanel.add(questionPanel);
            }
            surveyPanel.add(chapterPanel);
        }

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] components = surveyPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        if(checkBox.isSelected()) {
                            SurveyDirector surveyDirector = new SurveyDirector();
                        }
                    }
                }
            }
        });
    }

    public int showChapters(List<Chapter> chapters) {
        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        chapters.forEach(chapter -> {
            String row = chapter.getId() + " - " + chapter.getChapterTitle();
            dropDown.addItem(row);
            map.put(row, chapter.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la capitulos",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int idcha = map.get(text);
            return idcha;
        }
        return 0;
    }

    public int showCategories(List<Category> categories) {
        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        categories.forEach(category -> {
            String row = category.getId() + " - " + category.getName();
            dropDown.addItem(row);
            map.put(row, category.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la categoria",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);
            return id;
        }
        return 0;
    }

    public int showQuestions(List<Question> questions) {
        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        questions.forEach(question -> {
            String row = question.getId() + " - " + question.getQuestionText();
            dropDown.addItem(row);
            map.put(row, question.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la pregunta",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);
            return id;
        }
        return 0;
    }
    public ResponseOptions showResponseOpt(List<ResponseOptions> responses) {

        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        responses.forEach(response -> {
            String row = response.getId() + " - " + response.getOptionText();
            dropDown.addItem(row);
            map.put(row, response.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la respuesta",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);

            return findResponseOptionsUseCase.execute(id).orElseGet(() -> {
                System.out.println("Error");
                return null;
            });
        }
        return null;
    }

    public int showSubResponseOpt(List<SubresponseOptions> subresponses) {
        if (subresponses.isEmpty()) {
            // No hay subrespuestas, retornar 0 o cualquier valor predeterminado que
            // represente la ausencia de selección.
            return 0;
        }

        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        subresponses.forEach(subresponse -> {
            String row = subresponse.getId() + " - " + subresponse.getSubresponseText();
            dropDown.addItem(row);
            map.put(row, subresponse.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la subrespuesta",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);
            return id;
        }
        // El usuario canceló la selección, se puede considerar que no se ha
        // seleccionado ninguna opción.
        return 0;
    }
}