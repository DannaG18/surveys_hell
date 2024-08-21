package com.surveys_hell.ui.crud_ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.surveys_hell.chapter.infrastructure.controller.ChapterController;
import com.surveys_hell.question.infrastructure.controller.QuestionController;
import com.surveys_hell.survey.infrastructure.controller.SurveyController;

public class CrudUi extends JFrame{
    public CrudUi() {
        String options = "1. Survey CRUD\n2. Chapter CRUD\n3. Question CRUD\n4. Exit";
        int op;
        try {
            do { 
                op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
                switch (op) {
                    case 1:
                        new SurveyController();
                        break;
                    case 2:
                        new ChapterController();
                        break;
                    case 3:
                        new QuestionController();
                        break;
                    case 4:
                        break;
                    default:
                    JOptionPane.showMessageDialog(null, "Error: Opcion Invalida");
                }
            } while (op!=4);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

}
