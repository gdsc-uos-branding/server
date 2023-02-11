package com.gdscuos.recruit.domain.applicant.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    NONE("NONE", "NONE"),
    COMMON("COMMON", "COMMON"),
    TEAM("TEAM", "TEAM"),
    CORE("CORE", "CORE"),
    COMPLETE("COMPLETE", "COMPLETE");

    private final String key;
    private final String title;
}
