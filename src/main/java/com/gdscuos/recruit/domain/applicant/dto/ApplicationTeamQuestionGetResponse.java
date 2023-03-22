package com.gdscuos.recruit.domain.applicant.dto;

import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplicationTeamQuestionGetResponse {

    private int maxLength;
    private String question;
    private boolean required;

    public ApplicationTeamQuestionGetResponse(ApplicationQuestion applicationQuestion) {
        this.maxLength = applicationQuestion.getMaxLength();
        this.question = applicationQuestion.getQuestion();
        this.required = applicationQuestion.getRequired();
    }
}
