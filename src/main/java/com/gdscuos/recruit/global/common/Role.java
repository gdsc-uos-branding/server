package com.gdscuos.recruit.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    APPLICANT("ROLE_APPLICANT", "지원자"),
    MEMBER("ROLE_MEMBER", "멤버"),
    CORE("ROLE_CORE", "코어멤버"),
    LEAD("ROLE_LEAD", "리드");

    private final String key;
    private final String title;
}
