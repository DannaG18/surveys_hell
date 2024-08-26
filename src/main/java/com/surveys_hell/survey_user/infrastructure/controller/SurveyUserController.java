package com.surveys_hell.survey_user.infrastructure.controller;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.surveys_hell.survey_user.application.CreateSurveyUserUseCase;
import com.surveys_hell.survey_user.application.DeleteSurveyUserUseCase;
import com.surveys_hell.survey_user.application.FindSurveyUserUseCase;
import com.surveys_hell.survey_user.application.UpdateSurveyUserUseCase;
import com.surveys_hell.survey_user.domain.entity.SurveyUser;
import com.surveys_hell.survey_user.domain.service.SurveyUserService;
import com.surveys_hell.survey_user.infrastructure.repository.SurveyUserRepository;

public class SurveyUserController extends JFrame{
    SurveyUserService surveyUserService;
    CreateSurveyUserUseCase createSurveyUserUseCase;
    FindSurveyUserUseCase findSurveyUserUseCase;
    UpdateSurveyUserUseCase updateSurveyUserUseCase;
    DeleteSurveyUserUseCase deleteSurveyUserUseCase;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    public SurveyUserController() {
        surveyUserService = new SurveyUserRepository();
        createSurveyUserUseCase = new CreateSurveyUserUseCase(surveyUserService);
        findSurveyUserUseCase = new FindSurveyUserUseCase(surveyUserService);
        updateSurveyUserUseCase = new UpdateSurveyUserUseCase(surveyUserService);
        deleteSurveyUserUseCase = new DeleteSurveyUserUseCase(surveyUserService);

                // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu imagen
        setIconImage(windowIcon.getImage());
        setTitle("UsersRoles Management Menu");
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
        JPanel addPanel = createOperationPanel("Add SurveyUser", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search SurveyUser", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update SurveyUser", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete SurveyUser", "Delete", createDeletePanel());

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
        ImageIcon icon = new ImageIcon("src/main/resources/img/Admi.png");
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
        JPanel headerPanel = createHeaderPanel("Survey Users CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add SurveyUser");
        JButton searchButton = createRoundedButton("Search SurveyUser");
        JButton updateButton = createRoundedButton("Update SurveyUser");
        JButton deleteButton = createRoundedButton("Delete SurveyUser");
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
    
        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
    
        JLabel surveyLabel = new JLabel("Enter Survey ID:");
        JLabel userLabel = new JLabel("Enter User ID:");
        JTextField surveyField = new JTextField(10);
        JTextField userField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(surveyLabel);
        formPanel.add(surveyField);
        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                // Leer y convertir los valores de los campos de texto
                int surveyId = Integer.parseInt(surveyField.getText().trim());
                int userId = Integer.parseInt(userField.getText().trim());
                
                // Crear el objeto SurveyUser y asignar los valores
                SurveyUser surveyUser = new SurveyUser();
                surveyUser.setSurvey_id(surveyId); // Asignar el ID de usuario
                surveyUser.setUser_id(userId); // Asignar el ID de rol
                
                // Ejecutar el caso de uso para crear SurveyUser
                createSurveyUserUseCase.execute(surveyUser);
                JOptionPane.showMessageDialog(this, "SurveyUser added successfully.");
    
                // Limpiar los campos de texto
                surveyField.setText("");
                userField.setText("");
            } catch (NumberFormatException ex) {
                // Mostrar mensaje de error si los valores no son enteros válidos
                JOptionPane.showMessageDialog(this, "Please enter valid integer values for Survey ID and User ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
    
        JLabel surveyIdLabel = new JLabel("Enter Survey ID:");
        JLabel userIdLabel = new JLabel("Enter User ID:");
        
        JTextField surveyIdField = new JTextField(10);
        JTextField userIdField = new JTextField(10);
        
        JButton submitButton = createRoundedButton("Search");
        submitButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón
    
        JButton backButton = createRoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón
    
        formPanel.add(surveyIdLabel);
        formPanel.add(surveyIdField);
        formPanel.add(userIdLabel);
        formPanel.add(userIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int surveyId = Integer.parseInt(surveyIdField.getText().trim());
                int userId = Integer.parseInt(userIdField.getText().trim());
    
                findSurveyUserUseCase.execute(surveyId, userId).ifPresentOrElse(
                    SurveyUser -> showSurveyUserDetails(SurveyUser),
                    () -> JOptionPane.showMessageDialog(this, "SurveyUser not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
                
                surveyIdField.setText(""); // Limpiar el campo de texto
                userIdField.setText(""); // Limpiar el campo de texto
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
    
        JLabel surveyIdLabel = new JLabel("Enter new Users ID:");
        JTextField surveyIdField = new JTextField(20);
        JLabel userIdLabel = new JLabel("Enter new Roles ID:");
        JTextField userIdField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(surveyIdLabel);
        formPanel.add(surveyIdField);
        formPanel.add(userIdLabel);
        formPanel.add(userIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int surveyId = Integer.parseInt(surveyIdField.getText().trim());
                int userId = Integer.parseInt(userIdField.getText().trim());
    
                findSurveyUserUseCase.execute(surveyId, userId).ifPresentOrElse(
                    SurveyUser -> {
                        // En este caso, no hay cambios a realizar en el nombre.
                        // Simplemente se actualiza la entidad con los ID proporcionados.
                        updateSurveyUserUseCase.execute(SurveyUser);
                        JOptionPane.showMessageDialog(this, "SurveyUser updated successfully.");
                        surveyIdField.setText("");
                        userIdField.setText("");
                    },
                    () -> JOptionPane.showMessageDialog(this, "SurveyUser not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
    
        JLabel surveyIdLabel = new JLabel("Enter Users ID:");
        JLabel userIdLabel = new JLabel("Enter Roles ID:");
        
        JTextField surveyIdField = new JTextField(10);
        JTextField userIdField = new JTextField(10);
        
        JButton submitButton = createRoundedButton("Delete");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(surveyIdLabel);
        formPanel.add(surveyIdField);
        formPanel.add(userIdLabel);
        formPanel.add(userIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int surveyId = Integer.parseInt(surveyIdField.getText().trim());
                int userId = Integer.parseInt(userIdField.getText().trim());
    
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this SurveyUser?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteSurveyUserUseCase.execute(surveyId, userId);
                    JOptionPane.showMessageDialog(this, "SurveyUser deleted successfully.");
                    surveyIdField.setText("");
                    userIdField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private void showSurveyUserDetails(SurveyUser surveyUser) {
        String details = String.format("""
                SurveyUser found:
                Survey ID: %d
                User ID: %d
                """, surveyUser.getSurvey_id(), surveyUser.getUser_id());
        JOptionPane.showMessageDialog(this, details, "SurveyUser Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
