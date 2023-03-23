package com.gdscuos.recruit.domain.applicant.domain;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "application_question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Enumerated(EnumType.STRING)
    @Column(name = "team", nullable = false)
    private Team team;

    @Column(name = "question", nullable = false)
    private String question;

    @Setter
    @Column(name = "answer")
    private String answer;

    @Column(name = "required", nullable = false)
    private Boolean required;

    @Column(name = "max_length", nullable = false)
    private Integer maxLength;

    @Builder
    public ApplicationQuestion(Application application, Team team, String question,
            String answer,
            Boolean required, Integer maxLength) {
        this.application = application;
        this.team = team;
        this.question = question;
        this.answer = answer;
        this.required = required;
        this.maxLength = maxLength;
    }
}
