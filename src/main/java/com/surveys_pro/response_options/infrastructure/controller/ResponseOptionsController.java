package com.surveys_pro.response_options.infrastructure.controller;

import java.awt.*;
import java.sql.Date;
import javax.swing.*;

import com.surveys_pro.response_options.application.CreateResponseOptionsUseCase;
import com.surveys_pro.response_options.application.DeleteResponseOptionsUseCase;
import com.surveys_pro.response_options.application.FindResponseOptionsUseCase;
import com.surveys_pro.response_options.application.UpdateResponseOptionsUseCase;
import com.surveys_pro.response_options.domain.entity.ResponseOptions;
import com.surveys_pro.response_options.domain.service.ResponseOptionsService;
import com.surveys_pro.response_options.infrastructure.repository.ResponseOptionsRepository;

public class ResponseOptionsController extends JFrame {
    ResponseOptionsService responseOptionsService;
    CreateResponseOptionsUseCase createResponseOptionsUseCase;
    FindResponseOptionsUseCase findResponseOptionsUseCase;
    UpdateResponseOptionsUseCase updateResponseOptionsUseCase;
    DeleteResponseOptionsUseCase deleteResponseOptionsUseCase;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public ResponseOptionsController() {
        responseOptionsService = new ResponseOptionsRepository();
        createResponseOptionsUseCase = new CreateResponseOptionsUseCase(responseOptionsService);
        findResponseOptionsUseCase = new FindResponseOptionsUseCase(responseOptionsService);
        updateResponseOptionsUseCase = new UpdateResponseOptionsUseCase(responseOptionsService);
        deleteResponseOptionsUseCase = new DeleteResponseOptionsUseCase(responseOptionsService);

        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Response Options Management Menu");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "Menu");

        JPanel addPanel = createOperationPanel("Add Response Option", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Response Option", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Response Option", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Response Option", "Delete", createDeletePanel());

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

        JPanel headerPanel = createHeaderPanel("Response Options CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton addButton = createRoundedButton("Add Response Option");
        JButton searchButton = createRoundedButton("Search Response Option");
        JButton updateButton = createRoundedButton("Update Response Option");
        JButton deleteButton = createRoundedButton("Delete Response Option");
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

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        JLabel optionValueLabel = new JLabel("Enter Option Value:");
        JLabel categoryCatalogIdLabel = new JLabel("Enter Category Catalog ID:");
        JLabel parentResponseLabel = new JLabel("Enter Parent Response:");
        JLabel questionIdLabel = new JLabel("Enter Question ID:");
        JLabel commentResponseLabel = new JLabel("Enter Comment Response:");
        JLabel optionTextLabel = new JLabel("Enter Option Text:");

        JTextField optionValueField = new JTextField(10);
        optionValueField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField categoryCatalogIdField = new JTextField(10);
        categoryCatalogIdField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField parentResponseField = new JTextField(10);
        parentResponseField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField questionIdField = new JTextField(10);
        questionIdField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField commentResponseField = new JTextField(10);
        commentResponseField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField optionTextField = new JTextField(10);
        optionTextField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(optionValueLabel);
        formPanel.add(optionValueField);
        formPanel.add(categoryCatalogIdLabel);
        formPanel.add(categoryCatalogIdField);
        formPanel.add(parentResponseLabel);
        formPanel.add(parentResponseField);
        formPanel.add(questionIdLabel);
        formPanel.add(questionIdField);
        formPanel.add(commentResponseLabel);
        formPanel.add(commentResponseField);
        formPanel.add(optionTextLabel);
        formPanel.add(optionTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String optionValue = optionValueField.getText().trim();
            String categoryCatalogId = categoryCatalogIdField.getText().trim();
            String parentResponse = parentResponseField.getText().trim();
            String questionId = questionIdField.getText().trim();
            String commentResponse = commentResponseField.getText().trim();
            String optionText = optionTextField.getText().trim();
            Date createdAt = new Date(System.currentTimeMillis());
            Date updatedAt = new Date(System.currentTimeMillis());

            if (optionValue.isEmpty() || categoryCatalogId.isEmpty() || parentResponse.isEmpty() || questionId.isEmpty() || commentResponse.isEmpty() || optionText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ResponseOptions responseOptions = new ResponseOptions();
                try {
                    responseOptions.setOptionValue(Integer.parseInt(optionValue));
                    responseOptions.setCategoryCatalogId(Integer.parseInt(categoryCatalogId));
                    responseOptions.setParentResponse(Integer.parseInt(parentResponse));
                    responseOptions.setQuestionId(Integer.parseInt(questionId));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                responseOptions.setCommentReponse(commentResponse);
                responseOptions.setOptionText(optionText);
                responseOptions.setCreatedAt(createdAt);
                responseOptions.setUpdatedAt(updatedAt);

                createResponseOptionsUseCase.execute(responseOptions);
                JOptionPane.showMessageDialog(this, "Response Option added successfully.");
                optionValueField.setText("");
                categoryCatalogIdField.setText("");
                parentResponseField.setText("");
                questionIdField.setText("");
                commentResponseField.setText("");
                optionTextField.setText("");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Response Option ID:");
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
                findResponseOptionsUseCase.execute(id).ifPresentOrElse(
                        responseOptions -> showResponseOptionsDetails(responseOptions),
                        () -> JOptionPane.showMessageDialog(this, "Response Option not found.", "Error", JOptionPane.ERROR_MESSAGE)
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

        JPanel formPanel = new JPanel(new GridLayout(8, 1, 10, 10));

        JLabel optionValueLabel = new JLabel("Enter Option Value:");
        JLabel categoryCatalogIdLabel = new JLabel("Enter Category Catalog ID:");
        JLabel parentResponseLabel = new JLabel("Enter Parent Response:");
        JLabel questionIdLabel = new JLabel("Enter Question ID:");
        JLabel commentResponseLabel = new JLabel("Enter Comment Response:");
        JLabel optionTextLabel = new JLabel("Enter Option Text:");

        JTextField optionValueField = new JTextField(10);
        optionValueField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField categoryCatalogIdField = new JTextField(10);
        categoryCatalogIdField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField parentResponseField = new JTextField(10);
        parentResponseField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField questionIdField = new JTextField(10);
        questionIdField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField commentResponseField = new JTextField(10);
        commentResponseField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField optionTextField = new JTextField(10);
        optionTextField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(optionValueLabel);
        formPanel.add(optionValueField);
        formPanel.add(categoryCatalogIdLabel);
        formPanel.add(categoryCatalogIdField);
        formPanel.add(parentResponseLabel);
        formPanel.add(parentResponseField);
        formPanel.add(questionIdLabel);
        formPanel.add(questionIdField);
        formPanel.add(commentResponseLabel);
        formPanel.add(commentResponseField);
        formPanel.add(optionTextLabel);
        formPanel.add(optionTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                String optionValue = optionValueField.getText().trim();
                String categoryCatalogId = categoryCatalogIdField.getText().trim();
                String parentResponse = parentResponseField.getText().trim();
                String questionId = questionIdField.getText().trim();
                String commentResponse = commentResponseField.getText().trim();
                String optionText = optionTextField.getText().trim();

                if (optionValue.isEmpty() || categoryCatalogId.isEmpty() || parentResponse.isEmpty() || questionId.isEmpty() || commentResponse.isEmpty() || optionText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ResponseOptions responseOptions = new ResponseOptions();
                    try {
                        responseOptions.setOptionValue(Integer.parseInt(optionValue));
                        responseOptions.setCategoryCatalogId(Integer.parseInt(categoryCatalogId));
                        responseOptions.setParentResponse(Integer.parseInt(parentResponse));
                        responseOptions.setQuestionId(Integer.parseInt(questionId));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    responseOptions.setCommentReponse(commentResponse);
                    responseOptions.setOptionText(optionText);
                    responseOptions.setUpdatedAt(new Date(System.currentTimeMillis()));

                    updateResponseOptionsUseCase.execute(responseOptions);
                    JOptionPane.showMessageDialog(this, "Response Option updated successfully.");

                    optionValueField.setText("");
                    categoryCatalogIdField.setText("");
                    parentResponseField.setText("");
                    questionIdField.setText("");
                    commentResponseField.setText("");
                    optionTextField.setText("");
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

        JLabel idLabel = new JLabel("Enter Response Option ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Response Option?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteResponseOptionsUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Response Option deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showResponseOptionsDetails(ResponseOptions responseOptions) {
        String details = String.format("""
                Response Option found:
                ID: %d
                Option Value: %d
                Category Catalog ID: %d
                Parent Response: %d
                Question ID: %d
                HTML Component Type: %s
                Comment Response: %s
                Option Text: %s
                Created: %s
                Updated: %s
                """, responseOptions.getId(), responseOptions.getOptionValue(), responseOptions.getCategoryCatalogId(),
                responseOptions.getParentResponse(), responseOptions.getQuestionId(), responseOptions.getTypeComponentHtml(),
                responseOptions.getCommentReponse(), responseOptions.getOptionText(), responseOptions.getCreatedAt(),
                responseOptions.getUpdatedAt());
        JOptionPane.showMessageDialog(this, details, "Response Option Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ResponseOptionsController();
    }
}
