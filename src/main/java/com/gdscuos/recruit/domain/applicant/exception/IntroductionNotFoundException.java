package com.gdscuos.recruit.domain.applicant.exception;

import com.gdscuos.recruit.global.error.exception.EntityNotFoundException;
import com.gdscuos.recruit.global.error.exception.ErrorCode;

public class IntroductionNotFoundException extends EntityNotFoundException {

    public IntroductionNotFoundException() {
        super(ErrorCode.INTRODUCTION_NOT_FOUND);
    }
}
