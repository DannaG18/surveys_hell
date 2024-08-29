package com.surveys_pro.question.domain.service;

import java.util.Optional;

import com.surveys_pro.dto.QuestionDto;
import com.surveys_pro.question.domain.entity.Question;

import java.util.List;


public interface QuestionService {
    void createQuestion (Question question);
    Optional<Question> findQuestion (int id);
    void updateQuestion (Question Question);
    void deleteQuestion (int id);
    List<Question> findQuestionByChapter(int questionId);
    List<QuestionDto> findQuestionDto (int id);
}
