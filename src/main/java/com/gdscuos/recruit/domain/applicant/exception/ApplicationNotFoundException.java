package com.gdscuos.recruit.domain.applicant.exception;

import com.gdscuos.recruit.global.error.exception.EntityNotFoundException;
import com.gdscuos.recruit.global.error.exception.ErrorCode;

public class ApplicationNotFoundException extends EntityNotFoundException {

    public ApplicationNotFoundException() {
        super(ErrorCode.APPLICATION_NOT_FOUND);
    }
}
