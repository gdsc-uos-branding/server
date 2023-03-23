package com.gdscuos.recruit.domain.applicant.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationNotFoundException;
import com.gdscuos.recruit.global.common.Team;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationQuestionRepositoryTest {

    @Autowired
    private ApplicationQuestionRepository applicationQuestionRepository;
    @Autowired
    private ApplicationRepository applicationRepository;


    @Test
    @DisplayName("ApplicationQuestion 단건 저장")
    void saveOneApplicationQuestion() {
        //given
        Application application = applicationRepository.findById(3L)
                .orElseThrow(ApplicationNotFoundException::new);

        //when
        ApplicationQuestion question = ApplicationQuestion.builder()
                .application(application)
                .team(Team.COMMON)
                .question("question")
                .answer("")
                .maxLength(100)
                .required(true)
                .build();

        ApplicationQuestion savedQuestion = applicationQuestionRepository.save(question);

        //then
        assertThat(savedQuestion.getId()).isNotNull();
        assertThat(savedQuestion).isEqualTo(question);
    }

    @Test
    @DisplayName("ApplicationQuestion Bulk 저장")
    void saveBulkApplicationQuestion() {
        //given
        Application application = applicationRepository.findById(3L)
                .orElseThrow(ApplicationNotFoundException::new);

        //when
        List<ApplicationQuestion> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            questions.add(ApplicationQuestion.builder()
                    .application(application)
                    .team(Team.COMMON)
                    .question("question" + i)
                    .answer("")
                    .maxLength(100)
                    .required(true)
                    .build());
        }

        List<ApplicationQuestion> savedQuestions = applicationQuestionRepository.saveAll(
                questions);

        //then
        for (ApplicationQuestion savedQuestion : savedQuestions) {
            assertThat(savedQuestion.getId()).isNotNull();
        }
        assertThat(savedQuestions).isEqualTo(questions);
    }
}
