package com.gdscuos.recruit.domain.applicant.api;

import com.gdscuos.recruit.domain.applicant.dto.ApplicationGetResponse;
import com.gdscuos.recruit.domain.applicant.dto.ApplicationTeamQuestionGetResponse;
import com.gdscuos.recruit.domain.applicant.service.ApplicationService;
import com.gdscuos.recruit.global.auth.dto.UserDTO;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.util.SessionConst;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationApi {

    private final ApplicationService applicationService;

    @GetMapping("/question/{team}")
    public ResponseEntity<List<ApplicationTeamQuestionGetResponse>> getApplicationQuestion(
            @PathVariable Team team
    ) {
        List<ApplicationTeamQuestionGetResponse> teamQuestionList = applicationService.getTeamQuestion(
                team);

        return ResponseEntity.ok(teamQuestionList);
    }

    @GetMapping("")
    public ResponseEntity<ApplicationGetResponse> getApplication(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) UserDTO userDTO
    ) {
        ApplicationGetResponse application = applicationService.getApplication(userDTO.getEmail());

        return ResponseEntity.ok(application);
    }
//
//    @PostMapping("")
//    public ResponseEntity<String> submitApplication(
//            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) UserDTO userDTO
//    ) {
//
//    }
}
