package com.surveys_hell;

import com.surveys_hell.question.infrastructure.controller.QuestionController;
import com.surveys_hell.roles.infrastructure.controller.RolesController;
import com.surveys_hell.survey.infrastructure.controller.SurveyController;
import com.surveys_hell.ui.options_ui.OptionsUi;
import com.surveys_hell.users.infrastructure.controller.UsersController;
import com.surveys_hell.users_roles.infrastructure.controller.UsersRolesController;

public class Main {
    public static void main(String[] args) {
        new OptionsUi();
    }
}