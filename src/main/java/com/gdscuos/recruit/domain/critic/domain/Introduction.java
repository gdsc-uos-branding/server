package com.gdscuos.recruit.domain.critic.domain;

import com.gdscuos.recruit.global.common.Team;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "introduction")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Introduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "team", nullable = false)
    private Team team;

    @Column(name = "about")
    private String about;

    @Column(name = "activity")
    private String activity;

    @Column(name = "target")
    private String target;
}
