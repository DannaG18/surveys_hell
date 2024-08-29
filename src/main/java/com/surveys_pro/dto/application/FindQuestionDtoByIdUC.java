package com.surveys_pro.dto.application;

import com.surveys_pro.dto.QuestionDto;
import com.surveys_pro.question.domain.service.QuestionService;
import java.util.List;

public class FindQuestionDtoByIdUC {
    private QuestionService questionService;

    public FindQuestionDtoByIdUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<QuestionDto> execute(int id) {
        return questionService.findQuestionDto(id);
    }
}
