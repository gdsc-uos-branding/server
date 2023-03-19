package com.gdscuos.recruit.domain.applicant.repository;

import com.gdscuos.recruit.domain.applicant.domain.Application;
import com.gdscuos.recruit.domain.applicant.domain.Season;
import com.gdscuos.recruit.global.common.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUser(User user);

    Optional<Application> findByUserAndSeason(User user, Season season);
}
