package com.surveys_pro.chapter.infrastructure.controller;

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

import com.surveys_pro.chapter.application.CreateChapterUseCase;
import com.surveys_pro.chapter.application.DeleteChapterUseCase;
import com.surveys_pro.chapter.application.FindChapterUseCase;
import com.surveys_pro.chapter.application.UpdateChapterUseCase;
import com.surveys_pro.chapter.domain.entity.Chapter;
import com.surveys_pro.chapter.domain.service.ChapterService;
import com.surveys_pro.chapter.infrastructure.repository.ChapterRepository;

public class ChapterController extends JFrame{
    ChapterService chapterService;
    CreateChapterUseCase createChapterUseCase;
    FindChapterUseCase findChapterUseCase;
    UpdateChapterUseCase updateChapterUseCase;
    DeleteChapterUseCase deleteChapterUseCase;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public ChapterController() {
        chapterService = new ChapterRepository();
        createChapterUseCase = new CreateChapterUseCase(chapterService);
        findChapterUseCase = new FindChapterUseCase(chapterService);
        updateChapterUseCase = new UpdateChapterUseCase(chapterService);
        deleteChapterUseCase = new DeleteChapterUseCase(chapterService);

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

        JPanel addPanel = createOperationPanel("Add Chapter", "Add", createAddPanel());
        JPanel searchPanel = createOperationPanel("Search Chapter", "Search", createSearchPanel());
        JPanel updatePanel = createOperationPanel("Update Chapter", "Update", createUpdatePanel());
        JPanel deletePanel = createOperationPanel("Delete Chapter", "Delete", createDeletePanel());

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

        JPanel headerPanel = createHeaderPanel("Chapter CRUD");
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton addButton = createRoundedButton("Add Chapter");
        JButton searchButton = createRoundedButton("Search Chapter");
        JButton updateButton = createRoundedButton("Update Chapter");
        JButton deleteButton = createRoundedButton("Delete Chapter");
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
        

        JLabel titleLabel = new JLabel("Enter Chapter Title:");
        JLabel numberLabel = new JLabel("Enter Chapter Number:");
        JLabel surveyIdLabel = new JLabel("Enter Survey ID:");
    
        JTextField titleField = new JTextField(10);
        JTextField numberField = new JTextField(10);
        JTextField surveyIdField = new JTextField(10);
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(numberLabel);
        formPanel.add(numberField);
        formPanel.add(surveyIdLabel);
        formPanel.add(surveyIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            String surveyId = surveyIdField.getText().trim();
            String chapterTitle = titleField.getText().trim();
            String chapterNumber = numberField.getText().trim();
            Date createdAt = new Date(System.currentTimeMillis());
            Date updatedAt = new Date(System.currentTimeMillis());

            if (chapterTitle.isEmpty() || chapterNumber.isEmpty() || surveyId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Chapter chapter = new Chapter();
                try {
                    chapter.setSurveyId(Integer.parseInt(surveyId));
                    chapter.setChapterTitle(chapterTitle);
                    chapter.setChapterNumber(chapterNumber);
                    chapter.setCreatedAt(createdAt);
                    chapter.setUpdatedAt(updatedAt);
                    createChapterUseCase.execute(chapter);
                    JOptionPane.showMessageDialog(this, "Chapter added successfully.");
                    surveyIdField.setText("");
                    titleField.setText("");
                    numberField.setText("");
                    cardLayout.show(mainPanel, "Menu");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    surveyIdField.setText("");
                }
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel idLabel = new JLabel("Enter Chapter ID:");
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
                findChapterUseCase.execute(id).ifPresentOrElse(
                        chapters -> showChapterDetails(chapters),
                        () -> JOptionPane.showMessageDialog(this, "Chapter not found.", "Error", JOptionPane.ERROR_MESSAGE)
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
    
        JLabel idLabel = new JLabel("Enter Chapter ID:");
        JLabel titleLabel = new JLabel("Enter Chapter Title:");
        JLabel numberLabel = new JLabel("Enter Chapter Number:");
        JLabel surveyIdLabel = new JLabel("Enter Survey ID:");
    
        JTextField idField = new JTextField(10);
        JTextField titleField = new JTextField(10);
        JTextField numberField = new JTextField(10);
        JTextField surveyIdField = new JTextField(10);
        JButton submitButton = createRoundedButton("Update");
        JButton backButton = createRoundedButton("Back");
    
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(numberLabel);
        formPanel.add(numberField);
        formPanel.add(surveyIdLabel);
        formPanel.add(surveyIdField);
        formPanel.add(submitButton);
        formPanel.add(backButton);
    
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);
    
        panel.add(marginPanel, BorderLayout.CENTER);
    
        submitButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String title = titleField.getText().trim();
                String number = numberField.getText().trim();
                String surveyId = surveyIdField.getText().trim();
        
                if (title.isEmpty() || number.isEmpty() || id.isEmpty() || surveyId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "ID, Title, Number, and Survey ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Chapter chapter = new Chapter();
                    chapter.setId(Integer.parseInt(id));
                    chapter.setChapterTitle(title);
                    chapter.setChapterNumber(number);
                    chapter.setSurveyId(Integer.parseInt(surveyId));
                    updateChapterUseCase.execute(chapter);
                    JOptionPane.showMessageDialog(this, "Chapter updated successfully.");
        
                    idField.setText("");
                    titleField.setText(""); 
                    numberField.setText("");
                    surveyIdField.setText(""); 
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
                    deleteChapterUseCase.execute(id);
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

    private void showChapterDetails(Chapter chapter) {
        String details = String.format("""
                Roles found:
                ID: %d
                Title: %s
                Number: %s
                Created: %s
                Updated: %s
                Survey ID: %s
                """, chapter.getId(), chapter.getChapterTitle(), chapter.getChapterNumber(), chapter.getCreatedAt(), chapter.getUpdatedAt(), chapter.getSurveyId());
        JOptionPane.showMessageDialog(this, details, "Chapter Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ChapterController();
    }
}
