package com.gdscuos.recruit.domain.applicant.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationRepository;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.User;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationQuestionFindDaoTest {

    @Autowired
    private ApplicationQuestionFindDao applicationQuestionFindDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    @DisplayName("지원서 문항을 조회할 수 있다.")
    void getApplicationQuestion() {
        //given
        User user = userRepository.findUserByEmail("test@gmail.com")
                .orElseThrow(UserNotFoundException::new);
        Application application = applicationRepository.findByUser(user)
                .orElseThrow(ApplicationNotFoundException::new);

        //when
        List<ApplicationQuestion> applicationQuestion = applicationQuestionFindDao.getApplicationQuestion(
                application);

        //then
        assertThat(applicationQuestion.size()).isEqualTo(2);
    }
}
