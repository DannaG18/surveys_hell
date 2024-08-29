package com.surveys_pro.survey.infrastructure.controller;

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

import com.surveys_pro.survey.application.CreateSurveyUseCase;
import com.surveys_pro.survey.application.DeleteSurveyUseCase;
import com.surveys_pro.survey.application.FindSurveyUseCase;
import com.surveys_pro.survey.application.UpdateSurveyUseCase;
import com.surveys_pro.survey.domain.entity.Survey;
import com.surveys_pro.survey.domain.service.SurveyService;
import com.surveys_pro.survey.infrastructure.repository.SurveyRepository;

public class SurveyController extends JFrame{
    SurveyService surveyService;
    CreateSurveyUseCase createSurveyUseCase;
    FindSurveyUseCase findSurveyUseCase;
    UpdateSurveyUseCase updateSurveyUseCase;
    DeleteSurveyUseCase deleteSurveyUseCase;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    public SurveyController() {
        this.surveyService = new SurveyRepository();
        this.createSurveyUseCase = new CreateSurveyUseCase(surveyService);
        this.findSurveyUseCase = new FindSurveyUseCase(surveyService);
        this.updateSurveyUseCase = new UpdateSurveyUseCase(surveyService);
        this.deleteSurveyUseCase = new DeleteSurveyUseCase(surveyService);

        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); 
        setIconImage(windowIcon.getImage());
        setTitle("Survey Management Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panel principal con los botones del menú
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "Menu");

        // Paneles para cada operación
        JPanel addPanel = createOperationPanel("Add Survey", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Survey", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Survey", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Survey", "Delete", createDeletePanel());

        // Añadir los paneles al CardLayout
        mainPanel.add(addPanel, "Add");
        mainPanel.add(searchPanel, "Search");
        mainPanel.add(updatePanel, "Update");
        mainPanel.add(deletePanel, "Delete");

        // Añadir el panel principal al JFrame
        add(mainPanel);

        // Mostrar el menú inicial
        cardLayout.show(mainPanel, "Menu");

        // Hacer visible la ventana
        setVisible(true);
    }

    private JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Cargar una imagen pequeña
        ImageIcon icon = new ImageIcon("src/main/resources/img/Admi.png"); // Cambia esto a la ruta de tu imagen
        JLabel imageLabel = new JLabel(icon);

        // Añadir espacio debajo del encabezado
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(imageLabel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear encabezado solo para el menú
        JPanel headerPanel = createHeaderPanel("Survey CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Survey");
        JButton searchButton = createRoundedButton("Search Survey");
        JButton updateButton = createRoundedButton("Update Survey");
        JButton deleteButton = createRoundedButton("Delete Survey");
        JButton exitButton = createRoundedButton("Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        marginPanel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(marginPanel, BorderLayout.CENTER);

        // Action Listeners para cada botón
        addButton.addActionListener(e -> cardLayout.show(mainPanel, "Add"));
        searchButton.addActionListener(e -> cardLayout.show(mainPanel, "Search"));
        updateButton.addActionListener(e -> cardLayout.show(mainPanel, "Update"));
        deleteButton.addActionListener(e -> cardLayout.show(mainPanel, "Delete"));
        exitButton.addActionListener(e -> System.exit(0));

        return panel;
    }

    private JPanel createOperationPanel(String title, String cardName, JPanel operationPanel) {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear encabezado solo para operaciones
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
    
        JLabel nameLabel = new JLabel("Enter Survey Name:");
        JLabel descriptionLabel = new JLabel("Enter Survey Description:");
    
        JTextField nameField = new JTextField(10);
        JTextField descriptionField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            Date createdAt = new Date(System.currentTimeMillis());
            Date updatedAt = new Date(System.currentTimeMillis());


            if (name.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Survey survey = new Survey();
                survey.setName(name);
                survey.setDescription(description);
                survey.setCreatedAt(createdAt);
                survey.setUpdatedAt(updatedAt);
                createSurveyUseCase.execute(survey);
                JOptionPane.showMessageDialog(this, "User added successfully.");

                nameField.setText(""); 
                descriptionField.setText(""); 
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Survey ID:");
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
                findSurveyUseCase.execute(id).ifPresentOrElse(
                        surveys -> showSurveyDetails(surveys),
                        () -> JOptionPane.showMessageDialog(this, "Survey not found.", "Error", JOptionPane.ERROR_MESSAGE)
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
    
        JLabel idLabel = new JLabel("Enter Survey ID:");
        JLabel nameLabel = new JLabel("Enter Survey Name:");
        JLabel descriptionLabel = new JLabel("Enter Survey Description:");
    
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField descriptionField = new JTextField(10);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            Date updatedAt = new Date(System.currentTimeMillis());
    
            if (name.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Survey survey = new Survey();
                survey.setId(Integer.parseInt(id));
                survey.setName(name);
                survey.setDescription(description);
                survey.setUpdatedAt(updatedAt);
                updateSurveyUseCase.execute(survey);
                JOptionPane.showMessageDialog(this, "Survey updated successfully.");
    
                idField.setText("");
                nameField.setText(""); 
                descriptionField.setText(""); 
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Survey ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Survey?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteSurveyUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Survey deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showSurveyDetails(Survey survey) {
        String details = String.format("""
                Roles found:
                ID: %d
                Name: %s
                Created: %s
                Updated: %s
                Description: %s
                """, survey.getId(), survey.getName(), survey.getCreatedAt(), survey.getUpdatedAt(), survey.getDescription());
        JOptionPane.showMessageDialog(this, details, "Survey Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new SurveyController();
    }
}
