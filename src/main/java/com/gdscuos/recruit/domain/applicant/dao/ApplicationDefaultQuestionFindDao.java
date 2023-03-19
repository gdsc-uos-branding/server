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

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationDefaultQuestionFindDao {

    private final JPAQueryFactory queryFactory;

    public List<ApplicationQuestion> findDefaultAllQuestion(Long userId, Season season) {
        return queryFactory
                .selectFrom(applicationQuestion)
                .innerJoin(applicationQuestion.application, application)
                .where(application.user.id.eq(userId)
                        .and(application.season.eq(season)))
                .fetch();
    }
}
