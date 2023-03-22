package com.gdscuos.recruit.domain.applicant.repository;

import com.gdscuos.recruit.domain.applicant.domain.Introduction;
import com.gdscuos.recruit.global.common.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroductionRepository extends JpaRepository<Introduction, Long> {
    Introduction findByTeam(Team team);
}
