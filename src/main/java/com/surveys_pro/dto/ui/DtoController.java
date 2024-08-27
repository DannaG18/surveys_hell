package com.surveys_pro.dto.ui;

import com.surveys_pro.chapter.domain.service.ChapterService;
import com.surveys_pro.dto.*;
import com.surveys_pro.question.domain.service.QuestionService;
import com.surveys_pro.response_options.domain.service.ResponseOptionsService;
import com.surveys_pro.subresponse_options.domain.service.SubresponseOptionsService;
import com.surveys_pro.survey.domain.service.SurveyService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ItemEvent;

public class DtoController extends JFrame {
    private JButton nextButton, goBackButton;
    private Map<Integer, JLabel> labelMap = new HashMap<>();
    private Map<Integer, JCheckBox> responseOptionCheckboxMap = new HashMap<>();
    private Map<Integer, List<JCheckBox>> subResponseOptionCheckboxMap = new HashMap<>();
    private JScrollPane scrollPane;

    private Map<Integer, String> selectedResponseOptions = new HashMap<>();
    private Map<Integer, String> selectedSubResponseOptions = new HashMap<>();

    private SurveyService surveyService;
    private ChapterService chapterService;
    private QuestionService questionService;
    private ResponseOptionsService responseOptionService;
    private SubresponseOptionsService subResponseOptionService;

    public DtoController(SurveyService surveyService, ChapterService chapterService,
                         QuestionService questionService, ResponseOptionsService responseOptionService,
                         SubresponseOptionsService subResponseOptionService) {
        this.surveyService = surveyService;
        this.chapterService = chapterService;
        this.questionService = questionService;
        this.responseOptionService = responseOptionService;
        this.subResponseOptionService = subResponseOptionService;
    }

    public void startSurvey(int surveyId) {
        initComponents(surveyId);
        setVisible(true);
    }

    private void initComponents(int surveyId) {
        Optional<SurveyDto> foundSurvey = surveyService.findSurveyDto(surveyId);

        if (foundSurvey.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Survey not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Start Survey");
        setSize(700, 600);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/survey.png")).getImage());

        JLabel title = new JLabel(foundSurvey.get().getName());
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        for (ChapterDto chapter : foundSurvey.get().getChildChapter()) {
            JLabel label = new JLabel(chapter.getName());
            labelMap.put(chapter.getId(), label);
            gbc.gridy = row++;
            gbc.gridx = 0;
            contentPanel.add(label, gbc);

            List<QuestionDto> questions = chapter.getChildQuestion();
            for (QuestionDto question : questions) {
                JLabel labelQuestion = new JLabel(question.getName());
                labelMap.put(question.getId(), labelQuestion);
                gbc.gridy = row++;
                gbc.gridx = 1;
                contentPanel.add(labelQuestion, gbc);

                if ("single_choice".equals(question.getResponseType())) {
                    ButtonGroup buttonGroup = new ButtonGroup();
                    List<ResponseOptionsDto> responseOptions = question.getChildResponseOptions();

                    for (ResponseOptionsDto responseOption : responseOptions) {
                        if (responseOption.getId() == 0) {
                            JRadioButton radioButton = new JRadioButton(responseOption.getName());
                            gbc.gridy = row++;
                            gbc.gridx = 1;
                            contentPanel.add(radioButton, gbc);
                            buttonGroup.add(radioButton);

                            radioButton.addActionListener(e -> {
                                selectedResponseOptions.put(responseOption.getId(), responseOption.getName());
                            });

                            for (ResponseOptionsDto subOption : responseOptions) {
                                if (subOption.getParentResponseId() == responseOption.getId()) {
                                    JRadioButton subRadioButton = new JRadioButton(subOption.getName());
                                    subRadioButton.setVisible(false);
                                    gbc.gridy = row++;
                                    gbc.gridx = 2;
                                    contentPanel.add(subRadioButton, gbc);

                                    ButtonGroup subButtonGroup = new ButtonGroup();
                                    List<SubresponseOptionsDto> subResponseOptions = subOption.getChildSubresponse();
                                    List<JRadioButton> subOptionButtons = new ArrayList<>();

                                    for (SubresponseOptionsDto subResponseOption : subResponseOptions) {
                                        JRadioButton subSubOptionButton = new JRadioButton(subResponseOption.getName());
                                        subSubOptionButton.setVisible(false);
                                        gbc.gridy = row++;
                                        gbc.gridx = 3;
                                        contentPanel.add(subSubOptionButton, gbc);
                                        subButtonGroup.add(subSubOptionButton);
                                        subOptionButtons.add(subSubOptionButton);

                                        subSubOptionButton.addActionListener(e -> {
                                            selectedSubResponseOptions.put(subResponseOption.getId(), subResponseOption.getName());
                                        });
                                    }

                                    subRadioButton.addItemListener(e -> {
                                        boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                        for (JRadioButton subOptionButton : subOptionButtons) {
                                            subOptionButton.setVisible(selected);
                                        }
                                    });

                                    radioButton.addItemListener(e -> {
                                        boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                        subRadioButton.setVisible(selected);
                                    });
                                }
                            }

                            List<SubresponseOptionsDto> subResponseOptions = responseOption.getSubResponseOptions();
                            ButtonGroup subButtonGroup = new ButtonGroup();
                            List<JRadioButton> subOptionButtons = new ArrayList<>();

                            for (SubresponseOptionsDto subResponseOption : subResponseOptions) {
                                JRadioButton subSubOptionButton = new JRadioButton(subResponseOption.getSubResponseText());
                                subSubOptionButton.setVisible(false);
                                gbc.gridy = row++;
                                gbc.gridx = 2;
                                contentPanel.add(subSubOptionButton, gbc);
                                subButtonGroup.add(subSubOptionButton);
                                subOptionButtons.add(subSubOptionButton);

                                subSubOptionButton.addActionListener(e -> {
                                    selectedSubResponseOptions.put(subResponseOption.getId(), subResponseOption.getSubResponseText());
                                });
                            }

                            radioButton.addItemListener(e -> {
                                boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                for (JRadioButton subOptionButton : subOptionButtons) {
                                    subOptionButton.setVisible(selected);
                                }
                            });
                        }
                    }
                } else if ("multiple_choice".equals(question.getResponseType())) {
                    List<ResponseOptionsDto> responseOptions = question.getResponseOptions();
                    for (ResponseOptionsDto responseOption : responseOptions) {
                        JCheckBox checkBox = new JCheckBox(responseOption.getOptionText());
                        gbc.gridy = row++;
                        gbc.gridx = 1;
                        contentPanel.add(checkBox, gbc);

                        checkBox.addActionListener(e -> {
                            if (checkBox.isSelected()) {
                                selectedResponseOptions.put(responseOption.getId(), responseOption.getOptionText());
                            } else {
                                selectedResponseOptions.remove(responseOption.getId());
                            }
                        });

                        List<SubresponseOptionsDto> subResponseOptions = responseOption.getSubResponseOptions();
                        for (SubresponseOptionsDto subResponseOption : subResponseOptions) {
                            JCheckBox checkBoxSubRes = new JCheckBox(subResponseOption.getSubResponseText());
                            gbc.gridy = row++;
                            gbc.gridx = 2;
                            contentPanel.add(checkBoxSubRes, gbc);

                            checkBoxSubRes.addActionListener(e -> {
                                if (checkBoxSubRes.isSelected()) {
                                    selectedSubResponseOptions.put(subResponseOption.getId(), subResponseOption.getSubResponseText());
                                } else {
                                    selectedSubResponseOptions.remove(subResponseOption.getId());
                                }
                            });
                        }
                    }
                } else {
                    JTextField textField = new JTextField();
                    gbc.gridy = row++;
                    gbc.gridx = 1;
                    contentPanel.add(textField, gbc);

                    textField.addFocusListener(new FocusAdapter() {
                        public void focusLost(FocusEvent evt) {
                            selectedResponseOptions.put(question.getId(), textField.getText());
                        }
                    });
                }
            }
        }

        scrollPane = new JScrollPane(contentPanel);
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        nextButton = new JButton("Next");
        goBackButton = new JButton("🔙");
        goBackButton.addActionListener(e -> goBack());
        nextButton.addActionListener(e -> submitResponses());
        buttonPanel.add(nextButton);
        buttonPanel.add(goBackButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void submitResponses() {
        ResponseQuestionService responseQuestionService = new ResponseQuestionRepository();
        CreateResponseQuestionUseCase createResponseQuestionUseCase = new CreateResponseQuestionUseCase(responseQuestionService);

        // Handle selected ResponseOptions
        System.out.println("Selected Response Options:");
        for (Map.Entry<Integer, String> entry : selectedResponseOptions.entrySet()) {
            System.out.println("Option ID: " + entry.getKey() + ", Option Text: " + entry.getValue());
        }

        // Handle selected SubresponseOptions
        System.out.println("Selected Subresponse Options:");
        for (Map.Entry<Integer, String> entry : selectedSubResponseOptions.entrySet()) {
            System.out.println("Subresponse ID: " + entry.getKey() + ", Subresponse Text: " + entry.getValue());
        }

        // Here you can call your use case to save the selected responses
        createResponseQuestionUseCase.execute(selectedResponseOptions, selectedSubResponseOptions);
    }

    private void goBack() {
        // Handle the back navigation logic
        dispose(); // Close the current frame
        // Optionally, you could open a previous screen or main menu
    }
}

