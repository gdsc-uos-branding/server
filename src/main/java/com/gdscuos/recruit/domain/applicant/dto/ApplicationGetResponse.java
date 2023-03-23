package com.gdscuos.recruit.domain.applicant.dto;

import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.gdscuos.recruit.domain.applicant.domain.Status;
import com.gdscuos.recruit.global.common.Team;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationGetResponse {

    private Team team;
    private Season season;
    private Status status;
    private Boolean isApplyCore;
    private List<ApplicationQuestionAnswer> questionAnswerList;

    @Builder
    public ApplicationGetResponse(Team team, Season season, Status status, Boolean isApplyCore,
            List<ApplicationQuestionAnswer> questionAnswerList) {
        this.team = team;
        this.season = season;
        this.status = status;
        this.isApplyCore = isApplyCore;
        this.questionAnswerList = questionAnswerList;
    }
}
