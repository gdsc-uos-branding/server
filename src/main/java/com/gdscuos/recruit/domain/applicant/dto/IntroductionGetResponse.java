package com.gdscuos.recruit.domain.applicant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class IntroductionGetResponse {
    private String about;
    private String activity;
    private String target;

    @Builder
    public IntroductionGetResponse(String about, String activity, String target) {
        this.about = about;
        this.activity = activity;
        this.target = target;
    }
}
