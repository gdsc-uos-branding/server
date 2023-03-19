package com.gdscuos.recruit.domain.applicant.dao;

import static com.gdscuos.recruit.domain.applicant.domain.QApplicationQuestion.applicationQuestion;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationQuestionUpdateDao {

    private final JPAQueryFactory queryFactory;

    public void updateApplicationQuestion(Application application,
            List<ApplicationQuestion> applicationQuestions) {
        for (ApplicationQuestion newApplicationQuestion : applicationQuestions) {
            queryFactory.update(applicationQuestion)
                    .where(applicationQuestion.id.eq(newApplicationQuestion.getId()))
                    .set(applicationQuestion.answer, newApplicationQuestion.getAnswer())
                    .execute();
        }
    }
}
