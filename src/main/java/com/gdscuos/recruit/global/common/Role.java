package com.gdscuos.recruit.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    APPLICANT("ROLE_APPLICANT"),
    MEMBER("ROLE_MEMBER"),
    CORE("ROLE_CORE"),
    LEAD("ROLE_LEAD");

    private final String value;
}
