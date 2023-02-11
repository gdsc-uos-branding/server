package com.gdscuos.recruit.domain.critic.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Result {

    PASS("RESULT_PASS", "RESULT_PASS"),
    FAIL("RESULT_FAIL", "RESULT_FAIL");

    private final String key;
    private final String title;
}
