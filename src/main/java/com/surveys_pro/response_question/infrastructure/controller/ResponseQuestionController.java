package com.surveys_pro.response_question.infrastructure.controller;

import java.awt.*;
import javax.swing.*;

import com.surveys_pro.response_question.application.CreateResponseQuestionUseCase;
import com.surveys_pro.response_question.application.DeleteResponseQuestionUseCase;
import com.surveys_pro.response_question.application.FindResponseQuestionUseCase;
import com.surveys_pro.response_question.application.UpdateResponseQuestionUseCase;
import com.surveys_pro.response_question.domain.entity.ResponseQuestion;
import com.surveys_pro.response_question.domain.service.ResponseQuestionService;
import com.surveys_pro.response_question.infrastructure.repository.ResponseQuestionRepository;

public class ResponseQuestionController extends JFrame {
    ResponseQuestionService responseQuestionService;
    CreateResponseQuestionUseCase createResponseQuestionUseCase;
    FindResponseQuestionUseCase findResponseQuestionUseCase;
    UpdateResponseQuestionUseCase updateResponseQuestionUseCase;
    DeleteResponseQuestionUseCase deleteResponseQuestionUseCase;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public ResponseQuestionController() {
        responseQuestionService = new ResponseQuestionRepository();
        createResponseQuestionUseCase = new CreateResponseQuestionUseCase(responseQuestionService);
        findResponseQuestionUseCase = new FindResponseQuestionUseCase(responseQuestionService);
        updateResponseQuestionUseCase = new UpdateResponseQuestionUseCase(responseQuestionService);
        deleteResponseQuestionUseCase = new DeleteResponseQuestionUseCase(responseQuestionService);

        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Response Question Management Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "Menu");

        JPanel addPanel = createOperationPanel("Add Response Question", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Response Question", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Response Question", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Response Question", "Delete", createDeletePanel());

        mainPanel.add(addPanel, "Add");
        mainPanel.add(searchPanel, "Search");
        mainPanel.add(updatePanel, "Update");
        mainPanel.add(deletePanel, "Delete");

        add(mainPanel);

        cardLayout.show(mainPanel, "Menu");

        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Response Question CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton addButton = createRoundedButton("Add Response Question");
        JButton searchButton = createRoundedButton("Search Response Question");
        JButton updateButton = createRoundedButton("Update Response Question");
        JButton deleteButton = createRoundedButton("Delete Response Question");
        JButton exitButton = createRoundedButton("Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        marginPanel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(marginPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> cardLayout.show(mainPanel, "Add"));
        searchButton.addActionListener(e -> cardLayout.show(mainPanel, "Search"));
        updateButton.addActionListener(e -> cardLayout.show(mainPanel, "Update"));
        deleteButton.addActionListener(e -> cardLayout.show(mainPanel, "Delete"));
        exitButton.addActionListener(e -> System.exit(0));

        return panel;
    }

    private JPanel createOperationPanel(String title, String cardName, JPanel operationPanel) {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel headerPanel = createHeaderPanel(title);
        panel.add(headerPanel, BorderLayout.NORTH);

        panel.add(operationPanel, BorderLayout.CENTER);

        return panel;
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

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel subresponseIdLabel = new JLabel("Enter Subresponse ID:");
        JLabel responseTextLabel = new JLabel("Enter Response Text:");

        JTextField subresponseIdField = new JTextField(10);
        subresponseIdField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField responseTextField = new JTextField(10);
        responseTextField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(subresponseIdLabel);
        formPanel.add(subresponseIdField);
        formPanel.add(responseTextLabel);
        formPanel.add(responseTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String subresponseId = subresponseIdField.getText().trim();
            String responseText = responseTextField.getText().trim();

            if (subresponseId.isEmpty() || responseText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Response ID, Subresponse ID, and Response Text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ResponseQuestion responseQuestion = new ResponseQuestion();
                try {
                    responseQuestion.setSubresponseId(Integer.parseInt(subresponseId));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                responseQuestion.setresponseText(responseText);
                createResponseQuestionUseCase.execute(responseQuestion);
                JOptionPane.showMessageDialog(this, "Response Question added successfully.");
                subresponseIdField.setText("");
                responseTextField.setText("");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Response Question ID:");
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField idField = new JTextField(20);
        idField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton submitButton = createRoundedButton("Search");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                findResponseQuestionUseCase.execute(id).ifPresentOrElse(
                        responseQuestion -> showResponseQuestionDetails(responseQuestion),
                        () -> JOptionPane.showMessageDialog(this, "Response Question not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
                idField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel responseIdLabel = new JLabel("Enter Response ID:");
        JLabel subresponseIdLabel = new JLabel("Enter Subresponse ID:");
        JLabel responseTextLabel = new JLabel("Enter Response Text:");

        JTextField responseIdField = new JTextField(10);
        responseIdField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField subresponseIdField = new JTextField(10);
        subresponseIdField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField responseTextField = new JTextField(10);
        responseTextField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(responseIdLabel);
        formPanel.add(responseIdField);
        formPanel.add(subresponseIdLabel);
        formPanel.add(subresponseIdField);
        formPanel.add(responseTextLabel);
        formPanel.add(responseTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                String responseId = responseIdField.getText().trim();
                String subresponseId = subresponseIdField.getText().trim();
                String responseText = responseTextField.getText().trim();

                if (responseId.isEmpty() || subresponseId.isEmpty() || responseText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Response ID, Subresponse ID, and Response Text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ResponseQuestion responseQuestion = new ResponseQuestion();
                    try {
                        responseQuestion.setresponseId(Integer.parseInt(responseId));
                        responseQuestion.setSubresponseId(Integer.parseInt(subresponseId));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    responseQuestion.setresponseText(responseText);
                    updateResponseQuestionUseCase.execute(responseQuestion);
                    JOptionPane.showMessageDialog(this, "Response Question updated successfully.");

                    responseIdField.setText("");
                    subresponseIdField.setText("");
                    responseTextField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Response Question ID:");
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField idField = new JTextField(20);
        idField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton submitButton = createRoundedButton("Delete");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Response Question?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteResponseQuestionUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Response Question deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showResponseQuestionDetails(ResponseQuestion responseQuestion) {
        String details = String.format("""
                Response Question found:
                ID: %d
                Response ID: %d
                Subresponse ID: %d
                Response Text: %s
                """, responseQuestion.getId(), responseQuestion.getresponseId(), responseQuestion.getSubresponseId(), responseQuestion.getresponseText());
        JOptionPane.showMessageDialog(this, details, "Response Question Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ResponseQuestionController();
    }
}

