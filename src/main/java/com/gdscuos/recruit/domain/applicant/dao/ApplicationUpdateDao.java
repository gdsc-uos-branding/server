package com.gdscuos.recruit.domain.applicant.dao;

import static com.gdscuos.recruit.domain.applicant.domain.QApplication.application;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationSubmitRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationUpdateDao {

    private final JPAQueryFactory queryFactory;

    public void updateApplication(Application existApplication,
            ApplicationSubmitRequest applicationSubmitRequest) {
        queryFactory.update(application)
                .where(application.eq(existApplication))
                .set(application.team, applicationSubmitRequest.getTeam())
                .set(application.status, applicationSubmitRequest.getStatus())
                .set(application.isApplyCore, applicationSubmitRequest.getIsApplyCore())
                .execute();
    }
}
