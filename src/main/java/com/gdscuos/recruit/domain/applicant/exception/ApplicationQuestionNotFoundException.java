package com.gdscuos.recruit.domain.applicant.exception;

import com.gdscuos.recruit.global.error.exception.EntityNotFoundException;
import com.gdscuos.recruit.global.error.exception.ErrorCode;

public class ApplicationQuestionNotFoundException extends EntityNotFoundException {

    public ApplicationQuestionNotFoundException() {
        super(ErrorCode.Application_Question_NOT_FOUND);
    }
}
