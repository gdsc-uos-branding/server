package com.gdscuos.recruit.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Team {

    FRONTEND("FE", "TEAM_FRONTEND"),
    BACKEND("BE", "TEAM_BACKEND"),
    MOBILE("M", "TEAM_MOBILE"),
    DATA_ML("DM", "TEAM_DATA_ML"),
    CORE("C", "TEAM_CORE"),
    LEAD("L", "TEAM_LEAD");

    private final String key;
    private final String title;
}
