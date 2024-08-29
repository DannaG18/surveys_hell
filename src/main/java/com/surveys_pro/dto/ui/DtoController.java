package com.surveys_pro.dto.ui;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Collectors;

import com.surveys_pro.dto.ResponseOptionsDto;
import com.surveys_pro.dto.SurveyDto;
import com.surveys_pro.dto.application.FindSurveyDtoByIdUC;
import com.surveys_pro.survey.domain.service.SurveyService;
import com.surveys_pro.survey.infrastructure.repository.SurveyRepository;

public class DtoController {

    private SurveyService surveyService;
    private FindSurveyDtoByIdUC findSurveyDtoByIdUC;

    public DtoController() {
        this.surveyService = new SurveyRepository();
        this.findSurveyDtoByIdUC = new FindSurveyDtoByIdUC(surveyService);
    }

public void printSurveyDetails(int surveyId) {
    Optional<SurveyDto> surveyDto = findSurveyDtoByIdUC.execute(surveyId);
    surveyDto.ifPresentOrElse(survey -> {
        System.out.println(survey.getName());
        survey.getChildChapter().forEach(data -> {
            System.out.println(MessageFormat.format("Chapter {0} : {1}", data.getChapterNumber(), data.getName()));

            data.getChildQuestion().forEach(question -> {
                System.out.println(question.getName());

                question.getChildResponseOptions().stream()
                    .collect(Collectors.groupingBy(ResponseOptionsDto::getCategoryName))
                    .forEach((categoryName, responses) -> {
                        System.out.println(categoryName);  
                        responses.forEach(response -> {

                            System.out.println("- " + response.getName());
                            
                            response.getChildSubresponse().forEach(subresponse -> {
                                System.out.println("Subresponse: " + subresponse.getName());
                                System.out.println("- " + subresponse.getOptions());
                            });
                        });
                    });
            });
        });
    }, 
    () -> System.out.println("Survey not found"));
}


    public static void main(String[] args) {
        DtoController controller = new DtoController();
        controller.printSurveyDetails(2);
    }
}