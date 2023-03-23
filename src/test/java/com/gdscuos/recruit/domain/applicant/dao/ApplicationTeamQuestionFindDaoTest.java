package com.gdscuos.recruit.domain.applicant.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.global.common.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationTeamQuestionFindDaoTest {

    @Autowired
    private ApplicationTeamQuestionFindDao applicationTeamQuestionFindDao;

    @Test
    @DisplayName("정상적으로 Team Question을 반환한다.")
    void getTeamQuestion() {
        //given
        Team commonTeam = Team.COMMON;

        //when & then
        assertThat(applicationTeamQuestionFindDao.getApplicationQuestion(
                commonTeam).size()).isEqualTo(11);
    }
}
