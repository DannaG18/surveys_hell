package com.surveys_pro.login.infrastructure.controller;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.surveys_pro.login.application.LoginAutheticationUseCase;
import com.surveys_pro.login.application.LoginRolesUseCase;
import com.surveys_pro.login.domain.entity.LoginUsers;
import com.surveys_pro.login.domain.service.LoginService;
import com.surveys_pro.login.infrastructure.repository.LoginRepository;
import com.surveys_pro.ui.*;

public class LoginController extends JFrame implements ActionListener {

    private JPanel titlePanel;
    private JLabel title;
    private JLabel imageLabel;
    private JPanel contentPane;
    private JLabel username;
    private JTextField usernameField;
    private JLabel password;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginController() {
        initializeController();
    }

    private void initializeController() {
        ImageIcon windowIcon = new ImageIcon("src/main/resources/img/Hospital (4).png");
        setIconImage(windowIcon.getImage());
        setTitle("WELCOME");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Panel for title and image using FlowLayout
        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        titlePanel.setBounds(40, 50, 650, 130);

        // Title label
        title = new JLabel("Forum Surveys", SwingConstants.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD, 36));

        // Image label
        ImageIcon imageIcon = new ImageIcon("src/main/resources/img/Hospital (4).png");
        imageLabel = new JLabel(imageIcon);

        // Add title and image to the panel
        titlePanel.add(title);
        titlePanel.add(imageLabel);

        contentPane = new JPanel();
        contentPane.setBounds((getWidth() - 400) / 2, (getHeight() - 150) / 2, 400, 200);
        contentPane.setLayout(new GridLayout(0, 1, 10, 5));

        username = new JLabel("Username:");
        usernameField = new JTextField();
        password = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');

        contentPane.add(username);
        contentPane.add(usernameField);
        contentPane.add(password);
        contentPane.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds((getWidth() - 200) / 2, 470, 200, 40);
        loginButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        loginButton.addActionListener(this);

        add(titlePanel);
        add(contentPane);
        add(loginButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            LoginService loginService = new LoginRepository();
            LoginAutheticationUseCase loginAutheticationUseCase = new LoginAutheticationUseCase(loginService);
            Optional<LoginUsers> logged = loginAutheticationUseCase.login(user, pass);

            LoginRolesUseCase loginRolesUseCase = new LoginRolesUseCase(loginService);
            boolean admin = loginRolesUseCase.roles(logged.get().getId());


            if (admin) {
                setVisible(false);
                CrudUi menu = new CrudUi();
                menu.setResizable(false);
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);
            } else {
                setVisible(false);
                setVisible(false);
                OptionsUi menu = new OptionsUi();
                menu.setResizable(false);
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);
            }
        }
    }
}