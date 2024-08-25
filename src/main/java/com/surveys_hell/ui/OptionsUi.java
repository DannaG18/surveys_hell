/* package com.surveys_hell.ui;

import javax.swing.*;

import com.surveys_hell.login.infrastructure.controller.LoginController;
import com.surveys_hell.survey.application.GetAllSurveyUseCase;
import com.surveys_hell.survey.domain.service.SurveyService;
import com.surveys_hell.survey.infrastructure.repository.SurveyRepository;
import com.surveys_hell.ui.SurveyUi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import com.surveys_hell.survey.domain.entity.Survey;

public class OptionsUi extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private SurveyService surveyService;
    private GetAllSurveyUseCase getAllSurveyUseCase;
    private JComboBox<String> nameComboBox;
    private DefaultComboBoxModel<String> comboBoxModel;

    public OptionsUi() {
        surveyService = new SurveyRepository();
        getAllSurveyUseCase = new GetAllSurveyUseCase(surveyService);

        // JFrame Configuration
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Select Survey");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel addPanel = createOperationPanel("Select Survey", "Select", createAddPanel());

        mainPanel.add(addPanel, "Search");

        add(mainPanel);

        cardLayout.show(mainPanel, "Menu");

        setVisible(true);
    }

    private JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon("src/main/resources/img/Admi.png");
        JLabel imageLabel = new JLabel(icon);

        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(imageLabel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createOperationPanel(String title, String cardName, JPanel operationPanel) {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel headerPanel = createHeaderPanel(title);
        panel.add(headerPanel, BorderLayout.NORTH);

        panel.add(operationPanel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                super.setContentAreaFilled(false);
            }
        };
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        comboBoxModel = new DefaultComboBoxModel<>();
        nameComboBox = new JComboBox<>(comboBoxModel);
        nameComboBox.setPreferredSize(new Dimension(100, 30));

        updateSurveyOptions();

        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameComboBox);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        backButton.addActionListener(e -> new LoginController());

        // Add ActionListener to the JComboBox
        nameComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSurvey = (String) nameComboBox.getSelectedItem();
                if (selectedSurvey != null && !selectedSurvey.equals("Select an option")) {
                    // Redirect to a new UI or controller with the selected survey
                    new SurveyUi(selectedSurvey); // Open a new UI with survey information
                }
            }
        });

        return panel;
    }

    private void updateSurveyOptions() {
        comboBoxModel.removeAllElements(); 
        comboBoxModel.addElement("Select an option");

        List<Survey> surveys = new ArrayList<>(); 
        getAllSurveyUseCase.execute(surveys);
        for (Survey survey : surveys) {
            comboBoxModel.addElement(survey.getId() + " - " + survey.getName()); 
        }
    }

    public static void main(String[] args) {
        new OptionsUi();
    }
} */
