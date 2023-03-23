package com.gdscuos.recruit.domain.applicant.api;


import com.gdscuos.recruit.domain.applicant.dto.IntroductionGetResponse;
import com.gdscuos.recruit.domain.applicant.service.IntroductionService;
import com.gdscuos.recruit.global.common.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/introduction")
public class IntroductionApi {

    private final IntroductionService introductionService;

    @GetMapping("/{team}")
    public ResponseEntity<IntroductionGetResponse> getTeamIntroduction(@PathVariable Team team) {
        IntroductionGetResponse introductionResponse = introductionService.getIntroduction(team);
        return ResponseEntity.ok(introductionResponse);
    }
}
