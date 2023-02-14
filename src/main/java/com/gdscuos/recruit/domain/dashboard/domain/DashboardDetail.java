package com.gdscuos.recruit.domain.dashboard.domain;

import com.gdscuos.recruit.global.common.Team;
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
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dashboard_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DashboardDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dashboard_id")
    private Dashboard dashboard;

    @Enumerated(EnumType.STRING)
    @Column(name = "team")
    private Team team;

    @Column(name = "team_apply_cnt")
    private Integer teamApplyCount;

    @Column(name = "team_marked_cnt")
    private Integer teamMarkedCount;

    @Column(name = "team_member_cnt")
    private Integer teamMemberCount;
}
