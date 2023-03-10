package com.gdscuos.recruit.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Team {

    FRONTEND("TEAM_FRONTEND"),
    BACKEND("TEAM_BACKEND"),
    MOBILE("TEAM_MOBILE"),
    DATA_ML("TEAM_DATA_ML"),
    CORE("TEAM_CORE"),
    LEAD("TEAM_LEAD");

    private final String value;
}
