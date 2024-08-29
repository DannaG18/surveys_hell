package com.surveys_pro.subresponse_options.infrastructure.controller;

import java.awt.*;
import java.sql.Date;
import javax.swing.*;

import com.surveys_pro.subresponse_options.application.CreateSubresponseOptionsUseCase;
import com.surveys_pro.subresponse_options.application.DeleteSubresponseOptionsUseCase;
import com.surveys_pro.subresponse_options.application.FindSubresponseOptionsUseCase;
import com.surveys_pro.subresponse_options.application.UpdateSubresponseOptionsUseCase;
import com.surveys_pro.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_pro.subresponse_options.domain.service.SubresponseOptionsService;
import com.surveys_pro.subresponse_options.infrastructure.repository.SubresponseOptionsRepository;

public class SubresponseOptionsController extends JFrame {
    SubresponseOptionsService subresponseOptionsService;
    CreateSubresponseOptionsUseCase createSubresponseOptionsUseCase;
    FindSubresponseOptionsUseCase findSubresponseOptionsUseCase;
    UpdateSubresponseOptionsUseCase updateSubresponseOptionsUseCase;
    DeleteSubresponseOptionsUseCase deleteSubresponseOptionsUseCase;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public SubresponseOptionsController() {
        subresponseOptionsService = new SubresponseOptionsRepository();
        createSubresponseOptionsUseCase = new CreateSubresponseOptionsUseCase(subresponseOptionsService);
        findSubresponseOptionsUseCase = new FindSubresponseOptionsUseCase(subresponseOptionsService);
        updateSubresponseOptionsUseCase = new UpdateSubresponseOptionsUseCase(subresponseOptionsService);
        deleteSubresponseOptionsUseCase = new DeleteSubresponseOptionsUseCase(subresponseOptionsService);

        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Subresponse Options Management Menu");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "Menu");

        JPanel addPanel = createOperationPanel("Add Subresponse Option", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Subresponse Option", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Subresponse Option", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Subresponse Option", "Delete", createDeletePanel());

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

        JPanel headerPanel = createHeaderPanel("Subresponse Options CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton addButton = createRoundedButton("Add Subresponse Option");
        JButton searchButton = createRoundedButton("Search Subresponse Option");
        JButton updateButton = createRoundedButton("Update Subresponse Option");
        JButton deleteButton = createRoundedButton("Delete Subresponse Option");
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

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel subresponseNumberLabel = new JLabel("Enter Subresponse Number:");
        JLabel responseOptionsIdLabel = new JLabel("Enter Response Options ID:");
        JLabel subresponseTextLabel = new JLabel("Enter Subresponse Text:");

        JTextField subresponseNumberField = new JTextField(10);
        subresponseNumberField.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField responseOptionsIdField = new JTextField(10);
        responseOptionsIdField.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField subresponseTextField = new JTextField(10);
        subresponseTextField.setHorizontalAlignment(SwingConstants.CENTER);


        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(subresponseNumberLabel);
        formPanel.add(subresponseNumberField);
        formPanel.add(responseOptionsIdLabel);
        formPanel.add(responseOptionsIdField);
        formPanel.add(subresponseTextLabel);
        formPanel.add(subresponseTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String subresponseNumber = subresponseNumberField.getText().trim();
            String responseOptionsId = responseOptionsIdField.getText().trim();
            String subresponseText = subresponseTextField.getText().trim();
            Date createdAt = new Date(System.currentTimeMillis());
            Date updatedAt = new Date(System.currentTimeMillis());

            if (subresponseNumber.isEmpty() || responseOptionsId.isEmpty() || subresponseText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                SubresponseOptions subresponseOptions = new SubresponseOptions();
                try {
                    subresponseOptions.setsubresponseNumber(Integer.parseInt(subresponseNumber));
                    subresponseOptions.setResponseOptionsId(Integer.parseInt(responseOptionsId));
                    subresponseOptions.setSubresponseText(subresponseText);
                    subresponseOptions.setCreateAt(createdAt);
                    subresponseOptions.setUpdateAt(updatedAt);
                    createSubresponseOptionsUseCase.execute(subresponseOptions);
                    JOptionPane.showMessageDialog(this, "Subresponse Option added successfully.");
                    subresponseNumberField.setText("");
                    responseOptionsIdField.setText("");
                    subresponseTextField.setText("");
                    cardLayout.show(mainPanel, "Menu");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
                    subresponseNumberField.setText("");
                    responseOptionsIdField.setText("");
                    return;
                }
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Subresponse Option ID:");
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
                findSubresponseOptionsUseCase.execute(id).ifPresentOrElse(
                        subresponseOptions -> showSubresponseOptionsDetails(subresponseOptions),
                        () -> JOptionPane.showMessageDialog(this, "Subresponse Option not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
                idField.setText("");
                cardLayout.show(mainPanel, "Menu");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                idField.setText("");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel idLabel = new JLabel("Enter Subresponse Option ID:");
        JLabel subresponseNumberLabel = new JLabel("Enter Subresponse Number:");
        JLabel responseOptionsIdLabel = new JLabel("Enter Response Options ID:");
        JLabel subresponseTextLabel = new JLabel("Enter Subresponse Text:");

        JTextField idField = new JTextField(10);
        idField.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField subresponseNumberField = new JTextField(10);
        subresponseNumberField.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField responseOptionsIdField = new JTextField(10);
        responseOptionsIdField.setHorizontalAlignment(SwingConstants.CENTER);


        JTextField subresponseTextField = new JTextField(10);
        subresponseTextField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(subresponseNumberLabel);
        formPanel.add(subresponseNumberField);
        formPanel.add(responseOptionsIdLabel);
        formPanel.add(responseOptionsIdField);
        formPanel.add(subresponseTextLabel);
        formPanel.add(subresponseTextField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String subresponseNumber = subresponseNumberField.getText().trim();
                String responseOptionsId = responseOptionsIdField.getText().trim();
                String subresponseText = subresponseTextField.getText().trim();
                Date updatedAt = new Date(System.currentTimeMillis());

                if (subresponseNumber.isEmpty() || responseOptionsId.isEmpty() || subresponseText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    SubresponseOptions subresponseOptions = new SubresponseOptions();
                    subresponseOptions.setId(id);
                    try {
                        subresponseOptions.setsubresponseNumber(Integer.parseInt(subresponseNumber));
                        subresponseOptions.setResponseOptionsId(Integer.parseInt(responseOptionsId));
                        subresponseOptions.setSubresponseText(subresponseText);
                        subresponseOptions.setUpdateAt(updatedAt);
                        updateSubresponseOptionsUseCase.execute(subresponseOptions);
                        JOptionPane.showMessageDialog(this, "Subresponse Option updated successfully.");

                        idField.setText("");
                        subresponseNumberField.setText("");
                        responseOptionsIdField.setText("");
                        subresponseTextField.setText("");
                        cardLayout.show(mainPanel, "Menu");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
                        idField.setText("");
                        subresponseNumberField.setText("");
                        return;
                    }
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

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Subresponse Option ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Subresponse Option?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteSubresponseOptionsUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Subresponse Option deleted successfully.");
                    idField.setText("");
                }
                cardLayout.show(mainPanel, "Menu");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                idField.setText("");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showSubresponseOptionsDetails(SubresponseOptions subresponseOptions) {
        String details = String.format("""
                Subresponse Option found:
                ID: %d
                Subresponse Number: %d
                Response Options ID: %d
                Component HTML: %s
                Subresponse Text: %s
                Created: %s
                Updated: %s
                """, subresponseOptions.getId(), subresponseOptions.getsubresponseNumber(), subresponseOptions.getResponseOptionsId(),
                subresponseOptions.getComponentHtml(), subresponseOptions.getSubresponseText(), subresponseOptions.getCreateAt(),
                subresponseOptions.getUpdateAt());
        JOptionPane.showMessageDialog(this, details, "Subresponse Option Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new SubresponseOptionsController();
    }
}

