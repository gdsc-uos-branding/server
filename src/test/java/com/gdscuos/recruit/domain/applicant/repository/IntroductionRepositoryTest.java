package com.gdscuos.recruit.domain.applicant.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.global.common.Team;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class IntroductionRepositoryTest {

    @Autowired
    private IntroductionRepository introductionRepository;

    @Test
    @DisplayName("Introduction 값을 조회해올 수 있다.")
    void getIntroduction() {
        //given
        List<Team> teamList = Arrays.asList(Team.FRONTEND, Team.BACKEND, Team.DATA_ML, Team.DESIGN,
                Team.DATA_ML, Team.MOBILE);

        //when
        Collections.shuffle(teamList);

        //then
        assertThat(introductionRepository.findByTeam(teamList.get(0)))
                .extracting("about")
                .isInstanceOf(String.class);

        assertThat(introductionRepository.findByTeam(teamList.get(0)))
                .extracting("activity")
                .isInstanceOf(String.class);

        assertThat(introductionRepository.findByTeam(teamList.get(0)))
                .extracting("target")
                .isInstanceOf(String.class);
    }
}
