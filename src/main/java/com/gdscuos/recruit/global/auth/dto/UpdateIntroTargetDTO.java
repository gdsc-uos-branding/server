package com.gdscuos.recruit.global.auth.dto;

import com.gdscuos.recruit.global.common.Team;
import lombok.Data;

@Data
public class UpdateIntroTargetDTO {

    private final Team team;
    private final String target;
}