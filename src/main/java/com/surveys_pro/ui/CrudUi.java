package com.surveys_pro.ui;

import javax.swing.*;

import com.surveys_pro.chapter.infrastructure.controller.ChapterController;
import com.surveys_pro.login.infrastructure.controller.LoginController;
import com.surveys_pro.response_options.infrastructure.controller.ResponseOptionsController;
import com.surveys_pro.response_question.infrastructure.controller.ResponseQuestionController;
import com.surveys_pro.roles.infrastructure.controller.RolesController;
import com.surveys_pro.subresponse_options.infrastructure.controller.SubresponseOptionsController;
import com.surveys_pro.users.infrastructure.controller.UsersController;
import com.surveys_pro.users_roles.infrastructure.controller.UsersRolesController;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class CrudUi extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public CrudUi() {
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital.png");
        setIconImage(windowIcon.getImage());
        setTitle("Select Crud");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel addPanel = createOperationPanel("Select Crud", "Search", createAddPanel());

        mainPanel.add(addPanel, "Search");

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

        List<String> op = new ArrayList<>();
        op.add("Select an option");
        op.add("Roles CRUD");
        op.add("Users CRUD");
        op.add("Users Roles CRUD");
        op.add("Categories Catalog CRUD");
        op.add("Surveys CRUD");
        op.add("Chapter CRUD");
        op.add("Questions CRUD");
        op.add("Response Options CRUD");
        op.add("Subresponse Options CRUD");
        op.add("Response Question CRUD");

        String[] namesArray = op.toArray(new String[0]);
        JComboBox<String> nameComboBox = new JComboBox<>(namesArray);
        nameComboBox.setPreferredSize(new Dimension(100, 30));
        JButton submitButton = createRoundedButton("Submit");
        JButton backButton = createRoundedButton("Back");

        formPanel.add(nameComboBox);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        marginPanel.add(formPanel, BorderLayout.CENTER);

        panel.add(marginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = (String) nameComboBox.getSelectedItem();
        
            switch (name) {
                case "Roles CRUD":
                    new RolesController();
                    break;
                case "Users CRUD":
                    new UsersController();
                    break;
                case "Users Roles CRUD":
                    new UsersRolesController();
                    break;
                case "Categories Catalog CRUD":
                    // new CategoriesController();
                    break;
                case "Surveys CRUD":
                    // new SurveysController();
                    break;
                case "Chapter CRUD":
                    new ChapterController();
                    break;
                case "Questions CRUD":
                    // new QuestionsController();
                    break;
                case "Response Options CRUD":
                    new ResponseOptionsController();
                    break;
                case "Subresponse Options CRUD":
                    new SubresponseOptionsController();
                    break;
                case "Response Question CRUD":
                    new ResponseQuestionController();
                    break;
                case "Select an option":
                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid option.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });
        

        backButton.addActionListener(e -> new LoginController());

        return panel;
    }

    public static void main(String[] args) {
        new CrudUi();
    }
}