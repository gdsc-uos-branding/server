package com.gdscuos.recruit.domain.applicant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.gdscuos.recruit.domain.applicant.dto.IntroductionGetResponse;
import com.gdscuos.recruit.domain.applicant.exception.IntroductionNotFoundException;
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
class IntroductionServiceTest {

    @Autowired
    private IntroductionService introductionService;

    @Test
    @DisplayName("정상적으로 Introduction을 조회할 수 있다.")
    void getIntroduction() {
        //given
        List<Team> teamList = Arrays.asList(Team.FRONTEND, Team.BACKEND, Team.DATA_ML, Team.DESIGN,
                Team.DATA_ML, Team.MOBILE);

        //when
        Collections.shuffle(teamList);

        //then
        assertThat(introductionService.getIntroduction(teamList.get(0)))
                .isInstanceOf(IntroductionGetResponse.class);
    }

    @Test
    @DisplayName("Introudction이 없는 Team의 경우에 Exception을 발생시킨다.")
    void getAbnormalIntroduction() {
        //given
        List<Team> noIntroductionTeamList = Arrays.asList(Team.COMMON, Team.CORE, Team.LEAD);

        //when
        Collections.shuffle(noIntroductionTeamList);

        //then
        assertThatThrownBy(() -> introductionService.getIntroduction(noIntroductionTeamList.get(0)))
                .isInstanceOf(IntroductionNotFoundException.class);
    }
}
