package com.gdscuos.recruit.domain.applicant.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    NONE("NONE"),
    COMMON("COMMON"),
    TEAM("TEAM"),
    CORE("CORE"),
    COMPLETE("COMPLETE");

    private final String value;
}
