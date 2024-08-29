package com.surveys_pro.question.infrastructure.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.surveys_pro.question.application.CreateQuestionUseCase;
import com.surveys_pro.question.application.DeleteQuestionUseCase;
import com.surveys_pro.question.application.FindQuestionUseCase;
import com.surveys_pro.question.application.UpdateQuestionUseCase;
import com.surveys_pro.question.domain.entity.Question;
import com.surveys_pro.question.domain.service.QuestionService;
import com.surveys_pro.question.infrastructure.repository.QuestionRepository;

public class QuestionController extends JFrame{
    QuestionService questionService;
    CreateQuestionUseCase createQuestionUseCase;
    FindQuestionUseCase findQuestionUseCase;
    UpdateQuestionUseCase updateQuestionUseCase;
    DeleteQuestionUseCase deleteQuestionUseCase;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public QuestionController() {
        questionService = new QuestionRepository();
        createQuestionUseCase = new CreateQuestionUseCase(questionService);
        findQuestionUseCase = new FindQuestionUseCase(questionService);
        updateQuestionUseCase = new UpdateQuestionUseCase(questionService);
        deleteQuestionUseCase = new DeleteQuestionUseCase(questionService);

        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); 
        setIconImage(windowIcon.getImage());
        setTitle("Survey Management Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "Menu");

        JPanel addPanel = createOperationPanel("Add Question", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Question", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Question", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Question", "Delete", createDeletePanel());

        mainPanel.add(addPanel, "Add");
        mainPanel.add(searchPanel, "Search");
        mainPanel.add(updatePanel, "Update");
        mainPanel.add(deletePanel, "Delete");

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

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Question CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton addButton = createRoundedButton("Add Question");
        JButton searchButton = createRoundedButton("Search Question");
        JButton updateButton = createRoundedButton("Update Question");
        JButton deleteButton = createRoundedButton("Delete Question");
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
        
        JLabel chapterIdLabel = new JLabel("Enter Chapter ID:");
        JLabel numberLabel = new JLabel("Enter Question Number:");
        JLabel responseTypeLabel = new JLabel("Enter Response Type:");
        JLabel questionCommentLabel = new JLabel("Enter Question Comment:");
        JLabel questionTextLabel = new JLabel("Enter Question Text:");
    
        JTextField chapterIdField = new JTextField(10);
        JTextField numberField = new JTextField(10);
        JTextField responseTypeField = new JTextField(10);
        JTextField questionCommentField = new JTextField(10);
        JTextField questionTextField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(chapterIdLabel);
        formPanel.add(chapterIdField);
        formPanel.add(numberLabel);
        formPanel.add(numberField);
        formPanel.add(responseTypeLabel);
        formPanel.add(responseTypeField);
        formPanel.add(questionCommentLabel);
        formPanel.add(questionCommentField);
        formPanel.add(questionTextLabel);
        formPanel.add(questionTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            String chapterId = chapterIdField.getText().trim();
            String number = numberField.getText().trim();
            String responseType = responseTypeField.getText().trim();
            String questionComment = questionCommentField.getText().trim();
            String questionText = questionTextField.getText().trim();
            Date createdAt = new Date(System.currentTimeMillis());
            Date updatedAt = new Date(System.currentTimeMillis());


            if (number.isEmpty() || responseType.isEmpty() || chapterId.isEmpty() || responseType.isEmpty() || questionText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chapter ID, Question Number, Response Type, Question Commnet, and Question Text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Question question = new Question();
                try {
                    question.setChapterId(Integer.parseInt(chapterId));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                question.setQuestionNumber(number);
                question.setResponseType(responseType);
                question.setCreatedAt(createdAt);
                question.setUpdatedAt(updatedAt);
                question.setCommentQuestion(questionComment);
                question.setQuestionText(questionText);
                createQuestionUseCase.execute(question);
                JOptionPane.showMessageDialog(this, "Question added successfully.");
                chapterIdField.setText("");
                responseTypeField.setText("");
                numberField.setText("");
                questionCommentField.setText("");
                questionTextField.setText("");
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Question ID:");
        JTextField idField = new JTextField(20);
        JButton submitButton = createRoundedButton("Search");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                findQuestionUseCase.execute(id).ifPresentOrElse(
                        chapters -> showQuestionDetails(chapters),
                        () -> JOptionPane.showMessageDialog(this, "Question not found.", "Error", JOptionPane.ERROR_MESSAGE)
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
    
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
    
        JLabel chapterIdLabel = new JLabel("Enter Chapter ID:");
        JLabel numberLabel = new JLabel("Enter Question Number:");
        JLabel responseTypeLabel = new JLabel("Enter Response Type:");
        JLabel questionCommentLabel = new JLabel("Enter Question Comment:");
        JLabel questionTextLabel = new JLabel("Enter Question Text:");
    
        JTextField chapterIdField = new JTextField(10);
        JTextField numberField = new JTextField(10);
        JTextField responseTypeField = new JTextField(10);
        JTextField questionCommentField = new JTextField(10);
        JTextField questionTextField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(chapterIdLabel);
        formPanel.add(chapterIdField);
        formPanel.add(numberLabel);
        formPanel.add(numberField);
        formPanel.add(responseTypeLabel);
        formPanel.add(responseTypeField);
        formPanel.add(questionCommentLabel);
        formPanel.add(questionCommentField);
        formPanel.add(questionTextLabel);
        formPanel.add(questionTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                String chapterId = chapterIdField.getText().trim();
                String number = numberField.getText().trim();
                String responseType = responseTypeField.getText().trim();
                String questionComment = questionCommentField.getText().trim();
                String questionText = questionTextField.getText().trim();
        
                if (number.isEmpty() || responseType.isEmpty() || chapterId.isEmpty() || responseType.isEmpty() || questionText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "ID, Title, Number, and Survey ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Question question = new Question();
                    question.setQuestionNumber(number);
                    question.setResponseType(responseType);
                    question.setCommentQuestion(questionComment);
                    question.setQuestionText(questionText);
                    updateQuestionUseCase.execute(question);
                    JOptionPane.showMessageDialog(this, "Question updated successfully.");
        
                    chapterIdField.setText("");
                    responseTypeField.setText("");
                    numberField.setText("");
                    questionCommentField.setText("");
                    questionTextField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Chapter ID:");
        JTextField idField = new JTextField(20);
        JButton submitButton = createRoundedButton("Delete");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Question?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteQuestionUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Question deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showQuestionDetails(Question question) {
        String details = String.format("""
                Roles found:
                ID: %d
                Number: %s
                Response Type: %s
                Created: %s
                Updated: %s
                Question Comment: %s
                Question Text: %s
                """, question.getId(), question.getQuestionNumber(), question.getResponseType(), question.getCreatedAt(), question.getUpdatedAt(), question.getCommentQuestion(), question.getQuestionText());
        JOptionPane.showMessageDialog(this, details, "Question Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new QuestionController();
    }
}
