package com.surveys_pro.roles.infrastructure.controller;

import javax.swing.*;

import com.surveys_pro.roles.application.CreateRolesUseCase;
import com.surveys_pro.roles.application.DeleteRolesUseCase;
import com.surveys_pro.roles.application.FindRolesUseCase;
import com.surveys_pro.roles.application.UpdateRolesUseCase;
import com.surveys_pro.roles.domain.entity.Roles;
import com.surveys_pro.roles.domain.service.RolesService;
import com.surveys_pro.roles.infrastructure.repository.RolesRepository;

import java.awt.*;

public class RolesController extends JFrame {
    private final RolesService rolesService;
    private final CreateRolesUseCase createRolesUseCase;
    private final FindRolesUseCase findRolesUseCase;
    private final UpdateRolesUseCase updateRolesUseCase;
    private final DeleteRolesUseCase deleteRolesUseCase;

    private JPanel mainPanel; // Panel principal del menú
    private CardLayout cardLayout; // Layout para cambiar entre paneles

    public RolesController() {
        this.rolesService = new RolesRepository();
        this.createRolesUseCase = new CreateRolesUseCase(rolesService);
        this.findRolesUseCase = new FindRolesUseCase(rolesService);
        this.updateRolesUseCase = new UpdateRolesUseCase(rolesService);
        this.deleteRolesUseCase = new DeleteRolesUseCase(rolesService);

        // Configuración del JFrame
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png"); // Cambia esto a la ruta de tu imagen
        setIconImage(windowIcon.getImage());
        setTitle("Roles Management Menu");
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
        JPanel addPanel = createOperationPanel("Add Roles", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Roles", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Roles", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Roles", "Delete", createDeletePanel());

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
        JPanel headerPanel = createHeaderPanel("Roles CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Añadir márgenes alrededor de los botones
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales

        // Crear botones personalizados con esquinas redondeadas
        JButton addButton = createRoundedButton("Add Roles");
        JButton searchButton = createRoundedButton("Search Roles");
        JButton updateButton = createRoundedButton("Update Roles");
        JButton deleteButton = createRoundedButton("Delete Roles");
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

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel nameLabel = new JLabel("Enter Roles name:");
        JTextField nameField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        // Añadir márgenes alrededor del formulario
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Márgenes laterales
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Roles name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Roles roles = new Roles();
                roles.setName(name);
                createRolesUseCase.execute(roles);
                JOptionPane.showMessageDialog(this, "Roles added successfully.");
                nameField.setText(""); // Limpiar el campo de texto
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
    
        JLabel idLabel = new JLabel("Enter Roles ID:");
    
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
                findRolesUseCase.execute(id).ifPresentOrElse(
                    roles -> showRolesDetails(roles),
                    () -> JOptionPane.showMessageDialog(this, "Roles not found.", "Error", JOptionPane.ERROR_MESSAGE)
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

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Roles ID:");
        JTextField idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Enter new Roles name:");
        JTextField nameField = new JTextField(20);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
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
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Roles name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                findRolesUseCase.execute(id).ifPresentOrElse(
                        roles -> {
                            roles.setName(name);
                            updateRolesUseCase.execute(roles);
                            JOptionPane.showMessageDialog(this, "Roles updated successfully.");
                            idField.setText("");
                            nameField.setText("");
                        },
                        () -> JOptionPane.showMessageDialog(this, "Roles not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Roles ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Roles?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteRolesUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Roles deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showRolesDetails(Roles roles) {
        String details = String.format("""
                Roles found:
                ID: %d
                Name: %s
                """, roles.getId(), roles.getName());
        JOptionPane.showMessageDialog(this, details, "Roles Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new RolesController();
    }
}






