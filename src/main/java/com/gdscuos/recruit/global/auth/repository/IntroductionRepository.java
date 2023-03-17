package com.gdscuos.recruit.global.auth.repository;

import com.gdscuos.recruit.global.auth.domain.Introduction;
import com.gdscuos.recruit.global.common.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroductionRepository extends JpaRepository<Introduction, Long> {

    Introduction findIntroductionByTeam(Team team);
}