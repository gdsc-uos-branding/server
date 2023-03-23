package com.gdscuos.recruit.domain.applicant.domain;

import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "application")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "application")
    private List<ApplicationQuestion> applicationQuestions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "team", nullable = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(name = "season", nullable = false)
    private Season season;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "is_apply_core", nullable = false)
    private Boolean isApplyCore;

    @Builder
    public Application(User user, List<ApplicationQuestion> applicationQuestions, Team team,
            Season season, Status status, Boolean isApplyCore) {
        this.user = user;
        this.applicationQuestions = applicationQuestions;
        this.team = team;
        this.season = season;
        this.status = status;
        this.isApplyCore = isApplyCore;
    }
}
