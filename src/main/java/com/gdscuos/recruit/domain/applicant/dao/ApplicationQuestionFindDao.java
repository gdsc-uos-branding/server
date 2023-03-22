package com.gdscuos.recruit.domain.applicant.dao;

import static com.gdscuos.recruit.domain.applicant.domain.QApplicationQuestion.applicationQuestion;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 지원자의 지원서가 존재할 경우 기존 지원서의 질문을 가져오는 DAO
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationQuestionFindDao {

    private final JPAQueryFactory queryFactory;

    public List<ApplicationQuestion> getApplicationQuestion(Application application) {
        try {
            return queryFactory.selectFrom(applicationQuestion)
                    .where(applicationQuestion.application.eq(application))
                    .orderBy(applicationQuestion.id.asc())
                    .fetch();
        } catch (NullPointerException exception) {
            return null;
        }
    }
}
