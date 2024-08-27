package com.surveys_pro.users.infrastructure.controller;

import javax.swing.*;

import com.surveys_pro.users.application.CreateUsersUseCase;
import com.surveys_pro.users.application.DeleteUsersUseCase;
import com.surveys_pro.users.application.FindUsersUseCase;
import com.surveys_pro.users.application.UpdateUsersUseCase;
import com.surveys_pro.users.domain.entity.Users;
import com.surveys_pro.users.domain.service.UsersService;
import com.surveys_pro.users.infrastructure.controller.UsersController;
import com.surveys_pro.users.infrastructure.repository.UsersRepository;

import java.awt.*;

public class UsersController extends JFrame{
    private final UsersService usersService;
    private final CreateUsersUseCase createUsersUseCase;
    private final FindUsersUseCase findUsersUseCase;
    private final UpdateUsersUseCase updateUsersUseCase;
    private final DeleteUsersUseCase deleteUsersUseCase;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    public UsersController() {
        this.usersService = new UsersRepository();
        this.createUsersUseCase = new CreateUsersUseCase(usersService);
        this.findUsersUseCase = new FindUsersUseCase(usersService);
        this.updateUsersUseCase = new UpdateUsersUseCase(usersService);
        this.deleteUsersUseCase = new DeleteUsersUseCase(usersService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu imagen
        setIconImage(windowIcon.getImage());
        setTitle("Users Management Menu");
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
        JPanel addPanel = createOperationPanel("Add Users", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Users", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Users", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Users", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Users CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Users");
        JButton searchButton = createRoundedButton("Search Users");
        JButton updateButton = createRoundedButton("Update Users");
        JButton deleteButton = createRoundedButton("Delete Users");
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
    
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    
        JLabel enableLabel = new JLabel("Enter Enable User:");
        JLabel usernameLabel = new JLabel("Enter Username:");
        JLabel passwordLabel = new JLabel("Enter Password:");
    
        // Reemplazar JCheckBox con JComboBox
        String[] enableOptions = {"true", "false"};
        JComboBox<String> enableComboBox = new JComboBox<>(enableOptions);
        enableComboBox.setPreferredSize(new Dimension(100, 30)); // Ajustar el tamaño del JComboBox
    
        JTextField usernameField = new JTextField(10);
        JTextField passwordField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(enableLabel);
        formPanel.add(enableComboBox);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            // Obtener el valor booleano del JComboBox
            boolean enable = "true".equals(enableComboBox.getSelectedItem());
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
    
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Users users = new Users();
                users.setEnabled(enable);
                users.setUsername(username);
                users.setPassword(password);
                createUsersUseCase.execute(users);
                JOptionPane.showMessageDialog(this, "User added successfully.");
    
                enableComboBox.setSelectedIndex(0); // Restablecer el JComboBox a la opción por defecto
                usernameField.setText(""); // Limpiar el campo de texto
                passwordField.setText(""); // Limpiar el campo de texto
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }
    
    
    

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
    
        JLabel idLabel = new JLabel("Enter User ID:");
    
        JTextField idField = new JTextField(5);
        idField.setPreferredSize(new Dimension(300, 250)); // Ajusta la altura del campo de texto
    
        JButton submitButton = createRoundedButton("Search");
        submitButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón
    
        JButton backButton = createRoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Ajusta la altura del botón
    
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
                findUsersUseCase.execute(id).ifPresentOrElse(
                    users -> showUsersDetails(users),
                    () -> JOptionPane.showMessageDialog(this, "Users not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
                idField.setText(""); // Limpiar el campo de texto
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }
    

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
    
        JLabel enableLabel = new JLabel("Enter Enable User:");
        JLabel usernameLabel = new JLabel("Enter Username:");
        JLabel passwordLabel = new JLabel("Enter Password:");
    
        // Reemplazar JCheckBox con JComboBox
        String[] enableOptions = {"true", "false"};
        JComboBox<String> enableComboBox = new JComboBox<>(enableOptions);
        enableComboBox.setPreferredSize(new Dimension(100, 30)); // Ajustar el tamaño del JComboBox
    
        JTextField usernameField = new JTextField(10);
        JTextField passwordField = new JTextField(10);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(enableLabel);
        formPanel.add(enableComboBox);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            // Obtener el valor booleano del JComboBox
            boolean enable = "true".equals(enableComboBox.getSelectedItem());
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
    
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Users users = new Users();
                users.setEnabled(enable);
                users.setUsername(username);
                users.setPassword(password);
                updateUsersUseCase.execute(users);
                JOptionPane.showMessageDialog(this, "User updated successfully.");
    
                enableComboBox.setSelectedIndex(0); // Restablecer el JComboBox a la opción por defecto
                usernameField.setText(""); // Limpiar el campo de texto
                passwordField.setText(""); // Limpiar el campo de texto
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }
    

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Users ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Users?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteUsersUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Users deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showUsersDetails(Users users) {
        String details = String.format("""
                Users found:
                ID: %d
                Enable: %s
                UserName: %s
                Password: %s
                """, users.getId(), users.isEnabled(), users.getUsername(), users.getPassword());
        JOptionPane.showMessageDialog(this, details, "Users Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new UsersController();
    }
}
