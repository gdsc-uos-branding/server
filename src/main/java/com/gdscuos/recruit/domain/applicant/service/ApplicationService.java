package com.gdscuos.recruit.domain.applicant.service;

import com.gdscuos.recruit.domain.applicant.dao.ApplicationQuestionFindDao;
import com.gdscuos.recruit.domain.applicant.dao.ApplicationTeamQuestionFindDao;
import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationGetResponse;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationQuestionAnswer;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationTeamQuestionGetResponse;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationQuestionNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationRepository;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ApplicationTeamQuestionFindDao teamQuestionFindDao;
    private final ApplicationQuestionFindDao applicationQuestionFindDao;

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

    public ApplicationGetResponse getApplication(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        Application application = applicationRepository.findByUser(user)
                .orElseThrow(ApplicationNotFoundException::new);

        List<ApplicationQuestion> applicationQuestion = applicationQuestionFindDao.getApplicationQuestion(
                application);

        if (applicationQuestion == null) {
            throw new ApplicationQuestionNotFoundException();
        }

        List<ApplicationQuestionAnswer> applicationQuestionAnswers = applicationQuestion.stream()
                .map(ApplicationQuestionAnswer::new).collect(Collectors.toList());

        return ApplicationGetResponse.builder()
                .team(application.getTeam())
                .season(application.getSeason())
                .status(application.getStatus())
                .isApplyCore(application.getIsApplyCore())
                .questionAnswerList(applicationQuestionAnswers)
                .build();
    }

}
