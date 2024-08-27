package com.surveys_pro.categories_catalog.infrastructure.controller;

import java.awt.*;
import java.sql.Date;
import javax.swing.*;

import com.surveys_pro.categories_catalog.application.CreateCategoryUseCase;
import com.surveys_pro.categories_catalog.application.DeleteCategoryUseCase;
import com.surveys_pro.categories_catalog.application.FindCategoryUseCase;
import com.surveys_pro.categories_catalog.application.UpdateCategoryUseCase;
import com.surveys_pro.categories_catalog.domain.entity.Category;
import com.surveys_pro.categories_catalog.domain.service.CategoryService;
import com.surveys_pro.categories_catalog.infrastructure.repository.CategoryRepository;

public class CategoryController extends JFrame {
    CategoryService categoryService;
    CreateCategoryUseCase createCategoryUseCase;
    FindCategoryUseCase findCategoryUseCase;
    UpdateCategoryUseCase updateCategoryUseCase;
    DeleteCategoryUseCase deleteCategoryUseCase;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public CategoryController() {
        categoryService = new CategoryRepository();
        createCategoryUseCase = new CreateCategoryUseCase(categoryService);
        findCategoryUseCase = new FindCategoryUseCase(categoryService);
        updateCategoryUseCase = new UpdateCategoryUseCase(categoryService);
        deleteCategoryUseCase = new DeleteCategoryUseCase(categoryService);

        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Category Management Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "Menu");

        JPanel addPanel = createOperationPanel("Add Category", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Category", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Category", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Category", "Delete", createDeletePanel());

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

        JPanel headerPanel = createHeaderPanel("Category CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton addButton = createRoundedButton("Add Category");
        JButton searchButton = createRoundedButton("Search Category");
        JButton updateButton = createRoundedButton("Update Category");
        JButton deleteButton = createRoundedButton("Delete Category");
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

        JLabel nameLabel = new JLabel("Enter Category Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField nameField = new JTextField(10);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            Date createdAt = new Date(System.currentTimeMillis());
            Date updatedAt = new Date(System.currentTimeMillis());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Category Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Category category = new Category();
                category.setName(name);
                category.setCreatedAt(createdAt);
                category.setUpdateAt(updatedAt);
                createCategoryUseCase.execute(category);
                JOptionPane.showMessageDialog(this, "Category added successfully.");
                nameField.setText("");
            }

            cardLayout.show(mainPanel, "Menu");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Category ID:");
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
                findCategoryUseCase.execute(id).ifPresentOrElse(
                        category -> showCategoryDetails(category),
                        () -> JOptionPane.showMessageDialog(this, "Category not found.", "Error", JOptionPane.ERROR_MESSAGE)
                );
                idField.setText("");
                cardLayout.show(mainPanel, "Menu");
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

        JLabel idLabel = new JLabel("Enter Category ID:");
        JLabel nameLabel = new JLabel("Enter Category Name:");

        JTextField idField = new JTextField(10);
        idField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField nameField = new JTextField(10);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                Date updatedAt = new Date(System.currentTimeMillis());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Category Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Category category = new Category();
                    category.setId(id);
                    category.setName(name);
                    category.setUpdateAt(updatedAt);
                    updateCategoryUseCase.execute(category);
                    JOptionPane.showMessageDialog(this, "Category updated successfully.");
                    idField.setText("");
                    nameField.setText("");
                }
                cardLayout.show(mainPanel, "Menu");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                cardLayout.show(mainPanel, "Menu");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Category ID:");
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
                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Category?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteCategoryUseCase.execute(id);
                    JOptionPane.showMessageDialog(this, "Category deleted successfully.");
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        return panel;
    }

    private void showCategoryDetails(Category category) {
        String details = String.format("""
                Category found:
                ID: %d
                Name: %s
                Created: %s
                Updated: %s
                """, category.getId(), category.getName(), category.getCreatedAt(), category.getUpdateAt());
        JOptionPane.showMessageDialog(this, details, "Category Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new CategoryController();
    }
}
