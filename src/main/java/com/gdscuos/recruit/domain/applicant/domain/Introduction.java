package com.gdscuos.recruit.domain.applicant.domain;

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
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "introduction")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
