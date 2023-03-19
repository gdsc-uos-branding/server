package com.gdscuos.recruit.domain.applicant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.gdscuos.recruit.domain.applicant.domain.Status;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationGetResponse;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationSubmitRequest;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationTeamQuestionGetResponse;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationQuestionNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationRepository;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

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
    @DisplayName("Application 정상적으로 조회할 수 있다.")
    void getApplication() {
        //given
        String userEmail = "test@gmail.com";

        //when
        ApplicationGetResponse application = applicationService.getApplication(userEmail,
                Season.SS23);

        //then
        assertThat(application.getQuestionAnswerList().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("지원서를 정상적으로 업데이트할 수 있다.")
    void updateApplication() {
        //given
        ApplicationSubmitRequest applicationSubmitRequest = new ApplicationSubmitRequest();
        applicationSubmitRequest.setTeam(Team.BACKEND);
        applicationSubmitRequest.setSeason(Season.SS23);
        applicationSubmitRequest.setStatus(Status.COMPLETE);
        applicationSubmitRequest.setIsApplyCore(false);
        applicationSubmitRequest.setQuestionAnswerList(new ArrayList<ApplicationQuestion>());

        //when
        applicationService.getApplication("test@gmail.com", Season.SS23);
        em.flush();

        applicationService.submitApplication("test@gmail.com", Season.SS23,
                applicationSubmitRequest);
        em.flush();
        em.clear();

        //then
        User user = userRepository.findUserByEmail("test@gmail.com")
                .orElseThrow(UserNotFoundException::new);
        Application updatedApplication = applicationRepository.findByUserAndSeason(user,
                        Season.SS23)
                .orElseThrow(ApplicationQuestionNotFoundException::new);

        assertThat(updatedApplication.getTeam()).isEqualTo(Team.BACKEND);
        assertThat(updatedApplication.getStatus()).isEqualTo(Status.COMPLETE);

    }

}
