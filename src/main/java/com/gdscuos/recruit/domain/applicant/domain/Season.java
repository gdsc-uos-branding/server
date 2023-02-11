package com.gdscuos.recruit.domain.applicant.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Season {

    SS23("2023-S/S", "2023-S/S");
    private final String key;
    private final String title;
}
