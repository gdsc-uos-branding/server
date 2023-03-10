package com.gdscuos.recruit.global.auth.dto;

import com.gdscuos.recruit.global.common.User;
import lombok.Data;

@Data
public class UserDTO {

    private long userId;
    private String username;
    private String email;

    // Entity -> DTO
    public UserDTO(User entity) {
        this.userId = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
    }
}