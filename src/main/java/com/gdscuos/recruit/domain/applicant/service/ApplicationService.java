package com.gdscuos.recruit.domain.applicant.service;

import com.gdscuos.recruit.domain.applicant.dao.ApplicationTeamQuestionFindDao;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationTeamQuestionGetResponse;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationQuestionNotFoundException;
import com.gdscuos.recruit.global.common.Team;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationTeamQuestionFindDao teamQuestionFindDao;

    public List<ApplicationTeamQuestionGetResponse> getTeamQuestion(Team team) {
        List<ApplicationQuestion> applicationQuestionList = teamQuestionFindDao.getApplicationQuestion(
                team);

        if (applicationQuestionList == null) {
            throw new ApplicationQuestionNotFoundException();
        }

        return applicationQuestionList
                .stream()
                .map(ApplicationTeamQuestionGetResponse::new)
                .collect(Collectors.toList());
    }

}
