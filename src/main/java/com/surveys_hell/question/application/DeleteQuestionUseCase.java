package com.surveys_hell.question.application;

import com.surveys_hell.question.domain.service.QuestionService;

public class DeleteQuestionUseCase {
    private final QuestionService questionService;

    public DeleteQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void execute(int id) {
        questionService.deleteQuestion(id);
    }
}
