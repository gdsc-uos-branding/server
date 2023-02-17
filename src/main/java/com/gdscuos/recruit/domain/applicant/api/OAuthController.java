package com.gdscuos.recruit.domain.applicant.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @GetMapping("/auth") // TODO: 추후에 로그인 성공 이후 화면으로 이동할 수 있게끔 설정할 것
    public String getRequest() {
        return "welcome";
    }
}
