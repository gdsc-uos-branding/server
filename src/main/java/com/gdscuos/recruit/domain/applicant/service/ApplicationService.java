package com.gdscuos.recruit.domain.applicant.service;

import com.gdscuos.recruit.domain.applicant.dao.ApplicationDefaultQuestionFindDao;
import com.gdscuos.recruit.domain.applicant.dao.ApplicationQuestionFindDao;
import com.gdscuos.recruit.domain.applicant.dao.ApplicationQuestionUpdateDao;
import com.gdscuos.recruit.domain.applicant.dao.ApplicationTeamQuestionFindDao;
import com.gdscuos.recruit.domain.applicant.dao.ApplicationUpdateDao;
import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.gdscuos.recruit.domain.applicant.domain.Status;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationGetResponse;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationQuestionAnswer;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationSubmitRequest;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationTeamQuestionGetResponse;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.ApplicationQuestionNotFoundException;
import com.gdscuos.recruit.domain.applicant.exception.UserNotFoundException;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationQuestionRepository;
import com.gdscuos.recruit.domain.applicant.repository.ApplicationRepository;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    private final ApplicationQuestionUpdateDao applicationQuestionUpdateDao;
    private final ApplicationQuestionRepository applicationQuestionRepository;
    private final ApplicationDefaultQuestionFindDao applicationDefaultQuestionFindDao;
    private final ApplicationUpdateDao applicationUpdateDao;

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

    @Transactional
    public ApplicationGetResponse getApplication(String userEmail, Season season) {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        Application application = getOrCreateApplication(user, season);

        List<ApplicationQuestion> applicationQuestions = applicationQuestionRepository.findByApplication(
                        application, Sort.by("id").ascending())
                .orElseThrow(ApplicationQuestionNotFoundException::new);

        List<ApplicationQuestionAnswer> applicationQuestionAnswers = applicationQuestions.stream()
                .map(ApplicationQuestionAnswer::new)
                .collect(Collectors.toList());

        return ApplicationGetResponse.builder()
                .team(application.getTeam())
                .season(application.getSeason())
                .status(application.getStatus())
                .isApplyCore(application.getIsApplyCore())
                .questionAnswerList(applicationQuestionAnswers)
                .build();
    }

    private Application getOrCreateApplication(User user, Season season) {
        return applicationRepository.findByUserAndSeason(user, season)
                .orElseGet(() -> createDefaultApplication(user, season));
    }

    private Application createDefaultApplication(User user, Season season) {
        Application application = Application.builder()
                .user(user)
                .team(Team.COMMON)
                .season(season)
                .status(Status.NONE)
                .isApplyCore(false)
                .build();

        List<ApplicationQuestion> defaultQuestions = applicationDefaultQuestionFindDao
                .findDefaultAllQuestion(season)
                .stream()
                .map(defaultQuestion -> ApplicationQuestion.builder()
                        .application(application)
                        .team(defaultQuestion.getTeam())
                        .question(defaultQuestion.getQuestion())
                        .answer("")
                        .maxLength(defaultQuestion.getMaxLength())
                        .required(defaultQuestion.getRequired())
                        .build())
                .collect(Collectors.toList());

        applicationQuestionRepository.saveAll(defaultQuestions);

        return applicationRepository.save(application);
    }

    @Transactional
    public void submitApplication(String userEmail, Season season,
            ApplicationSubmitRequest applicationSubmitRequest) {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        Application application = applicationRepository.findByUserAndSeason(user, season)
                .orElseThrow(ApplicationNotFoundException::new);

        List<ApplicationQuestion> applicationQuestions = applicationSubmitRequest.getQuestionAnswerList();

        applicationUpdateDao.updateApplication(application, applicationSubmitRequest);

        applicationQuestionUpdateDao.updateApplicationQuestion(application,
                applicationQuestions);
    }

}
