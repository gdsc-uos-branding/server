package com.gdscuos.recruit.global.auth.dto;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.critic.domain.ApplicationCritic;
import com.gdscuos.recruit.global.common.Role;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private long userId;
    private Team team;
    private List<Application> applications;
    private List<ApplicationCritic> applicationCritics;
    private String username;
    private String email;
    private Role role;

    // Entity -> DTO mapping
    public UserDTO(User entity) {
        this.userId = entity.getId();
        this.team = entity.getTeam();
        this.applications = entity.getApplications();
        this.applicationCritics = entity.getApplicationCritics();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.role = entity.getRole();
    }

    // Entity -> return DTO
    public static UserDTO from(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .team(user.getTeam())
                .applications(user.getApplications())
                .applicationCritics(user.getApplicationCritics())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}