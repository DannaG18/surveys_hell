package com.surveys_pro.question.application;

import com.surveys_pro.question.domain.entity.Question;
import com.surveys_pro.question.domain.service.QuestionService;

public class CreateQuestionUseCase {
    private final QuestionService questionService;

    public CreateQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void execute(Question question) {
        questionService.createQuestion(question);
    }
}
