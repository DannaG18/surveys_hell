package com.surveys_pro.users_roles.infrastructure.controller;

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

import com.surveys_pro.users_roles.application.CreateUsersRolesUseCase;
import com.surveys_pro.users_roles.application.DeleteUsersRolesUseCase;
import com.surveys_pro.users_roles.application.FindUsersRolesUseCase;
import com.surveys_pro.users_roles.application.UpdateUsersRolesUseCase;
import com.surveys_pro.users_roles.domain.entity.UsersRoles;
import com.surveys_pro.users_roles.domain.service.UsersRolesService;
import com.surveys_pro.users_roles.infrastructure.repository.UsersRolesRepository;

public class UsersRolesController extends JFrame{
    private UsersRolesService usersRolesService;
    private CreateUsersRolesUseCase createUsersRolesUseCase;
    private FindUsersRolesUseCase findUsersRolesUseCase;
    private UpdateUsersRolesUseCase updateUsersRolesUseCase;
    private DeleteUsersRolesUseCase deleteUsersRolesUseCase;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    public UsersRolesController(){
        this.usersRolesService = new UsersRolesRepository();
        this.createUsersRolesUseCase = new CreateUsersRolesUseCase(usersRolesService);
        this.findUsersRolesUseCase = new FindUsersRolesUseCase(usersRolesService);
        this.updateUsersRolesUseCase = new UpdateUsersRolesUseCase(usersRolesService);
        this.deleteUsersRolesUseCase = new DeleteUsersRolesUseCase(usersRolesService);
    
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
        JPanel addPanel = createOperationPanel("Add UsersRoles", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search UsersRoles", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update UsersRoles", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete UsersRoles", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("UsersRoles CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add UsersRoles");
        JButton searchButton = createRoundedButton("Search UsersRoles");
        JButton updateButton = createRoundedButton("Update UsersRoles");
        JButton deleteButton = createRoundedButton("Delete UsersRoles");
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
    
        JLabel usersLabel = new JLabel("Enter Users ID:");
        JLabel rolesLabel = new JLabel("Enter Roles ID:");
        JTextField usersField = new JTextField(10);
        JTextField rolesField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(usersLabel);
        formPanel.add(usersField);
        formPanel.add(rolesLabel);
        formPanel.add(rolesField);
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
                int usersId = Integer.parseInt(usersField.getText().trim());
                int rolesId = Integer.parseInt(rolesField.getText().trim());
                
                // Crear el objeto UsersRoles y asignar los valores
                UsersRoles usersRoles = new UsersRoles();
                usersRoles.setUser_id(usersId); // Asignar el ID de usuario
                usersRoles.setRole_id(rolesId); // Asignar el ID de rol
                
                // Ejecutar el caso de uso para crear UsersRoles
                createUsersRolesUseCase.execute(usersRoles);
                JOptionPane.showMessageDialog(this, "UsersRoles added successfully.");
    
                // Limpiar los campos de texto
                usersField.setText("");
                rolesField.setText("");
            } catch (NumberFormatException ex) {
                // Mostrar mensaje de error si los valores no son enteros válidos
                JOptionPane.showMessageDialog(this, "Please enter valid integer values for Users ID and Roles ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }
    

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
    
        JLabel userIdLabel = new JLabel("Enter Users ID:");
        JLabel roleIdLabel = new JLabel("Enter Roles ID:");
        
        JTextField userIdField = new JTextField(10);
        JTextField roleIdField = new JTextField(10);
        
        JButton submitButton = createRoundedButton("Search");
        submitButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón
    
        JButton backButton = createRoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón
    
        formPanel.add(userIdLabel);
        formPanel.add(userIdField);
        formPanel.add(roleIdLabel);
        formPanel.add(roleIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText().trim());
                int roleId = Integer.parseInt(roleIdField.getText().trim());
    
                findUsersRolesUseCase.execute(userId, roleId).ifPresentOrElse(
                    usersRoles -> showUsersRolesDetails(usersRoles),
                    () -> JOptionPane.showMessageDialog(this, "UsersRoles not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
                
                userIdField.setText(""); // Limpiar el campo de texto
                roleIdField.setText(""); // Limpiar el campo de texto
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
    
        JLabel usersIdLabel = new JLabel("Enter new Users ID:");
        JTextField usersIdField = new JTextField(20);
        JLabel rolesIdLabel = new JLabel("Enter new Roles ID:");
        JTextField rolesIdField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(usersIdLabel);
        formPanel.add(usersIdField);
        formPanel.add(rolesIdLabel);
        formPanel.add(rolesIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int usersId = Integer.parseInt(usersIdField.getText().trim());
                int rolesId = Integer.parseInt(rolesIdField.getText().trim());
    
                findUsersRolesUseCase.execute(usersId, rolesId).ifPresentOrElse(
                    usersRoles -> {
                        // En este caso, no hay cambios a realizar en el nombre.
                        // Simplemente se actualiza la entidad con los ID proporcionados.
                        updateUsersRolesUseCase.execute(usersRoles);
                        JOptionPane.showMessageDialog(this, "UsersRoles updated successfully.");
                        usersIdField.setText("");
                        rolesIdField.setText("");
                    },
                    () -> JOptionPane.showMessageDialog(this, "UsersRoles not found.", "Error", JOptionPane.ERROR_MESSAGE)
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
    
        JLabel userIdLabel = new JLabel("Enter Users ID:");
        JLabel roleIdLabel = new JLabel("Enter Roles ID:");
        
        JTextField userIdField = new JTextField(10);
        JTextField roleIdField = new JTextField(10);
        
        JButton submitButton = createRoundedButton("Delete");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(userIdLabel);
        formPanel.add(userIdField);
        formPanel.add(roleIdLabel);
        formPanel.add(roleIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText().trim());
                int roleId = Integer.parseInt(roleIdField.getText().trim());
    
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this UsersRoles?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteUsersRolesUseCase.execute(userId, roleId);
                    JOptionPane.showMessageDialog(this, "UsersRoles deleted successfully.");
                    userIdField.setText("");
                    roleIdField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }
    

    private void showUsersRolesDetails(UsersRoles usersRoles) {
        String details = String.format("""
                UsersRoles found:
                Users ID: %d
                Roles ID: %d
                """, usersRoles.getUser_id(), usersRoles.getRole_id());
        JOptionPane.showMessageDialog(this, details, "UsersRoles Details", JOptionPane.INFORMATION_MESSAGE);
    }
    

    public static void main(String[] args) {
        new UsersRolesController();
    }
}

