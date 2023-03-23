package com.gdscuos.recruit.domain.applicant.dto;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.gdscuos.recruit.domain.applicant.domain.Status;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationSubmitRequest {

    @NotNull
    private Team team;

    @NotNull
    private Season season;

    @NotNull
    private Status status;
    @NotNull
    private Boolean isApplyCore;

    private List<ApplicationQuestion> questionAnswerList;

    public Application toApplication(User user) {
        return Application.builder()
                .user(user)
                .team(team)
                .season(season)
                .status(status)
                .isApplyCore(isApplyCore)
                .build();
    }

}
