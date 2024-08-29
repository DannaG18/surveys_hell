package com.surveys_pro.question.application;

import java.util.Optional;

import com.surveys_pro.question.domain.entity.Question;
import com.surveys_pro.question.domain.service.QuestionService;

public class FindQuestionUseCase {
    private final QuestionService questionService;

    public FindQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Optional<Question> execute(int id) {
        return questionService.findQuestion(id);
    }
}
