package com.surveys_hell.question.application;

import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.question.domain.service.QuestionService;

public class UpdateQuestionUseCase {
    private final QuestionService questionService;

    public UpdateQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void execute(Question question) {
        questionService.updateQuestion(question);
    }
}
