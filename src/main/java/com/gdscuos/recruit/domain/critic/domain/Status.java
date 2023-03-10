package com.gdscuos.recruit.domain.critic.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    DOCUMENT("STATUS_DOCUMENT"),
    INTERVIEW("STATUS_INTERVIEW");

    private final String value;
}
