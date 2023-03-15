package com.gdscuos.recruit.domain.applicant.service;

import com.gdscuos.recruit.domain.applicant.domain.Introduction;
import com.gdscuos.recruit.domain.applicant.dto.IntroductionGetResponse;
import com.gdscuos.recruit.domain.applicant.exception.IntroductionNotFoundException;
import com.gdscuos.recruit.domain.applicant.repository.IntroductionRepository;
import com.gdscuos.recruit.global.common.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IntroductionService {

    private final IntroductionRepository introductionRepository;

    public IntroductionGetResponse getIntroduction(Team team) {
        Introduction intro = introductionRepository.findByTeam(team);
        if (intro == null) {
            throw new IntroductionNotFoundException();
        }
        return IntroductionGetResponse.builder()
                .about(intro.getAbout())
                .activity(intro.getActivity())
                .target(intro.getTarget())
                .build();
    }
}
