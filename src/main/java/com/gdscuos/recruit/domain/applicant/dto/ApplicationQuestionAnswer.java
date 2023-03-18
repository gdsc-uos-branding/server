package com.gdscuos.recruit.domain.applicant.dto;

import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.global.common.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationQuestionAnswer {

    private Long id;

    private Team questionTeam;
    private String question;
    private String answer;
    private Boolean required;
    private Integer maxLength;

    public ApplicationQuestionAnswer(ApplicationQuestion applicationQuestion) {
        this.id = applicationQuestion.getId();
        this.questionTeam = applicationQuestion.getTeam();
        this.question = applicationQuestion.getQuestion();
        this.answer = applicationQuestion.getAnswer();
        this.required = applicationQuestion.getRequired();
        this.maxLength = applicationQuestion.getMaxLength();
    }
}
