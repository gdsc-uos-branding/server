package com.gdscuos.recruit.domain.applicant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.gdscuos.recruit.domain.applicant.dto.ApplicationGetResponse;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationTeamQuestionGetResponse;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationQuestionNotFoundException;
import com.gdscuos.recruit.global.common.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;

    @Test
    @DisplayName("Team별 Question을 정상적으로 반환")
    void getTeamQuestion() {
        //given
        Team common = Team.COMMON;

        //when
        List<ApplicationTeamQuestionGetResponse> teamQuestion = applicationService.getTeamQuestion(
                common);

        //then
        assertThat(teamQuestion.size()).isEqualTo(11);
    }

    @Test
    @DisplayName("Question이 등록되지 않은 Team에 대해 조회을 요청할 시에 Exception을 발생시킨다")
    void getQuestionUnregisteredTeam() {
        //given
        Team backend = Team.FRONTEND;

        //when & then
        assertThatThrownBy(() -> applicationService.getTeamQuestion(backend))
                .isInstanceOf(ApplicationQuestionNotFoundException.class);
    }

    @Test
    @DisplayName("Applcation을 정상적으로 조회할 수 있다.")
    void getApplication() {
        //given
        String userEmail = "test@gmail.com";

        //when
        ApplicationGetResponse application = applicationService.getApplication(userEmail);

        //then
        assertThat(application.getQuestionAnswerList().size()).isEqualTo(2);
    }


}
