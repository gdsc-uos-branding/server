package com.gdscuos.recruit.domain.applicant.dao;

import static com.gdscuos.recruit.domain.applicant.domain.QApplication.application;
import static com.gdscuos.recruit.domain.applicant.domain.QApplicationQuestion.applicationQuestion;

import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 지원자의 지원서를 초기 생성하기 위해 해당 시즌의 모든 질문을 가져오는 DAO
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationDefaultQuestionFindDao {

    private final JPAQueryFactory queryFactory;
    private final Long StorageId = 5L;

    public List<ApplicationQuestion> findDefaultAllQuestion(Season season) {
        return queryFactory
                .selectFrom(applicationQuestion)
                .innerJoin(applicationQuestion.application, application)
                .where(application.user.id.eq(StorageId)
                        .and(application.season.eq(season)))
                .fetch();
    }
}
