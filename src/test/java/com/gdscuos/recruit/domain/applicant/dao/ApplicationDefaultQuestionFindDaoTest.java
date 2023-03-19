package com.gdscuos.recruit.domain.applicant.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ApplicationDefaultQuestionFindDaoTest {

    @Autowired
    private ApplicationDefaultQuestionFindDao applicationDefaultQuestionFindDao;

    @Test
    @DisplayName("기본 질문들을 조회할 수 있다")
    void getDefaultQuestion() {
        //given & when
        List<ApplicationQuestion> defaultAllQuestion = applicationDefaultQuestionFindDao.findDefaultAllQuestion(
                5L, Season.SS23);
        //then
        assertThat(defaultAllQuestion.size()).isEqualTo(11);
    }
}
