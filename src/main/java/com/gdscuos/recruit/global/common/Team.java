package com.gdscuos.recruit.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Team {

    COMMON("COMMON"), // 첫 회원가입 시 팀 초기값
    FRONTEND("TEAM_FRONTEND"),
    BACKEND("TEAM_BACKEND"),
    MOBILE("TEAM_MOBILE"),
    DATA_ML("TEAM_DATA_ML"),
    DESIGN("TEAM_DESIGN"),
    CORE("TEAM_CORE"),
    LEAD("TEAM_LEAD");

    private final String value;
}
