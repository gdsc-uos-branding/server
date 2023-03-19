package com.gdscuos.recruit.domain.applicant.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationQuestionRepository;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationRepository;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationQuestionUpdateDaoTest {

    @Autowired
    private ApplicationQuestionUpdateDao applicationQuestionUpdateDao;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationQuestionRepository applicationQuestionRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("지원서 답변을 업데이트한다.")
    void updateApplicationQuestion() {
        //given
        User user = userRepository.findUserByEmail("test@gmail.com")
                .orElseThrow(UserNotFoundException::new);

        Application application = applicationRepository.findByUserAndSeason(user, Season.SS23)
                .orElseThrow(
                        ApplicationNotFoundException::new);

        ApplicationQuestion savedQuestion = applicationQuestionRepository.save(
                ApplicationQuestion.builder()
                        .application(application)
                        .question("test")
                        .answer("")
                        .maxLength(100)
                        .required(true)
                        .team(Team.COMMON)
                        .build()
        );

        //when
        savedQuestion.setAnswer("it's test");
        applicationQuestionUpdateDao.updateApplicationQuestion(application,
                Arrays.asList(savedQuestion));

        //then
        ApplicationQuestion updatedQuestion = applicationQuestionRepository.findById(
                savedQuestion.getId()).orElse(null);
        assertThat(updatedQuestion).isNotNull();
        assertThat(updatedQuestion.getAnswer()).isEqualTo("it's test");
    }
}
