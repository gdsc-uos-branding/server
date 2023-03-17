package com.gdscuos.recruit.global.auth.dto;

import com.gdscuos.recruit.global.common.Role;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private long userId;
    private String username;
    private String email;
    private Role role;
    private Team team;

    // Entity -> DTO mapping
    public UserDTO(User entity) {
        this.userId = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.role = entity.getRole();
    }

    // Entity -> return DTO
    public static UserDTO from(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .team(user.getTeam())
                .build();
    }
}