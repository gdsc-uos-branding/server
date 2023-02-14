package com.gdscuos.recruit.domain.dashboard.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dashboard")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "dashboard")
    private List<DashboardDetail> dashboardDetails = new ArrayList<>();

    @Column(name = "team_cnt")
    private Integer teamCount;

    @Column(name = "total_apply_cnt")
    private Integer totalApplyCount;

    @Column(name = "total_marked_cnt")
    private Integer totalMarkedCount;
}
