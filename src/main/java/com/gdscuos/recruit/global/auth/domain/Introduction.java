package com.gdscuos.recruit.global.auth.domain;

import com.gdscuos.recruit.global.common.Team;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "introduction")
@NoArgsConstructor
@Setter
public class Introduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "team", nullable = false)
    @Enumerated(EnumType.STRING)
    private Team team;

    @Column(name = "about")
    private String about;

    @Column(name = "activity")
    private String activity;

    @Column(name = "target")
    private String target;
}
