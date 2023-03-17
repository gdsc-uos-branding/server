package com.gdscuos.recruit.domain.applicant.repository;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User를 통해 지원서를 조회할 수 있다")
    void getApplicationByUser() {
        //given
        User user = userRepository.findUserByEmail("test@gmail.com")
                .orElseThrow(UserNotFoundException::new);
        //when
        Application application = applicationRepository.findByUser(user)
                .orElseThrow(ApplicationNotFoundException::new);
        //then
        Assertions.assertThat(application.getTeam()).isEqualTo(Team.FRONTEND);
    }
}
