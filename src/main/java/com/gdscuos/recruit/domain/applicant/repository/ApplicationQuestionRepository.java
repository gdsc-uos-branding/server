package com.gdscuos.recruit.domain.applicant.repository;

import com.gdscuos.recruit.domain.applicant.domain.ApplicationQuestion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationQuestionRepository extends JpaRepository<ApplicationQuestion, Long> {

    Optional<ApplicationQuestion> findById(Long id);
}
