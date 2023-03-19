package com.gdscuos.recruit.domain.applicant.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.gdscuos.recruit.domain.applicant.domain.Status;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationSubmitRequest;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationRepository;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationUpdateDaoTest {

    @Autowired
    private ApplicationUpdateDao applicationUpdateDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("지원서를 업데이트 한다.")
    void updateApplication() {
        //given
        User user = userRepository.findUserByEmail("test@gmail.com")
                .orElseThrow(UserNotFoundException::new);

        Application application = applicationRepository.findByUserAndSeason(user, Season.SS23)
                .orElseThrow(
                        ApplicationNotFoundException::new);

        //when
        ApplicationSubmitRequest applicationSubmitRequest = new ApplicationSubmitRequest();
        applicationSubmitRequest.setTeam(Team.BACKEND);
        applicationSubmitRequest.setSeason(Season.SS23);
        applicationSubmitRequest.setStatus(Status.COMPLETE);
        applicationSubmitRequest.setIsApplyCore(false);

        applicationUpdateDao.updateApplication(application, applicationSubmitRequest);
        em.flush();
        em.clear(); //context clear

        //then
        Application updatedApplication = applicationRepository.findById(application.getId())
                .orElse(null);

        assertThat(updatedApplication).isNotNull();
        assertThat(updatedApplication.getTeam()).isEqualTo(Team.BACKEND);
        assertThat(updatedApplication.getStatus()).isEqualTo(Status.COMPLETE);
    }
}
