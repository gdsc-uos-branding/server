package com.gdscuos.recruit.domain.applicant.dao;

import static com.gdscuos.recruit.domain.applicant.domain.QApplication.application;
import static com.gdscuos.recruit.domain.applicant.domain.QApplicationQuestion.applicationQuestion;

import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.global.common.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 각 팀별 지원서 문항 조회 DAO
 * <p>
 * Storage Id는 해당 시즌의 모든 질문을 보유하는 계정의 Id를 사용할 것
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationTeamQuestionFindDao {

    private final JPAQueryFactory queryFactory;
    private final Long StorageId = 5L;

    public List<ApplicationQuestion> getApplicationQuestion(Team team) {
        try {
            return queryFactory.selectFrom(application)
                    .leftJoin(application.applicationQuestions, applicationQuestion).fetchJoin()
                    .where(application.user.id.eq(StorageId), applicationQuestion.team.eq(team))
                    .orderBy(application.id.asc())
                    .fetchFirst()
                    .getApplicationQuestions();
        } catch (NullPointerException exception) {
            return null;
        }
    }
}
